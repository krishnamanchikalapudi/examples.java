#!/bin/bash

. ./config.sh

printf "\n%s\n\n" "----------- [START] DEPLOY: ${DATE_TIME} "

if $isContainerImageExists ; then   # isContainerImageExists=true
    printf "\n\n%s\n" " ------ Creating Container Image "
    . ./build.sh
fi

if $isNsExists ; then   # isNsExists=true
    printf "\n\n%s\n" " ------ Create K8S namespace "
    kubectl create namespace ${APP_NS}
    kubectl auth can-i create deployments --namespace ${APP_NS} 
    kubectl apply -f namespace-role.yaml 
fi


printf "\n\n%s\n" " ------ Deploying image: ${DOCKER_IMAGE} "
if $isNsExists ; then   # isNsExists=true
    kubectl run ${APP_NAME} --image=${DOCKER_IMAGE} --port=${APP_PORT} --expose=true --allow-missing-template-keys=true --namespace=${APP_NS} --serviceaccount=${APP_NAME}-serviceaccount
else    # isNsExists=false
    kubectl run ${APP_NAME} --image=${DOCKER_IMAGE} --port=${APP_PORT} --expose=true --allow-missing-template-keys=true 
fi

printf "\n\n%s\n" " ------ list of all the deployed objects"
if $isNsExists ; then      # isNsExists=true
    kubectl get deployment --namespace=${APP_NS}
else   # isNsExists=false
    kubectl get deployment 
fi


sleep 15
printf "\n\n%s\n" " ------ tail pod logs"
if $isNsExists ; then   # isNsExists=true
    POD_ID=`kubectl get pods --namespace=${APP_NS} | grep ${APP_NAME} | awk '{print $1}'`
    kubectl logs $POD_ID --namespace=${APP_NS} --follow=true &
else   # isNsExists=false
    POD_ID=`kubectl get pods | grep ${APP_NAME} | awk '{print $1}'`
    kubectl logs $POD_ID --follow=true &
fi
printf "\n\n\n"
# kubectl exec -ti springrestbootk8-85cb489bbf-27xpc bash
# kubectl exec springrestbootk8-85cb489bbf-27xpc env

# create role
# kubectl create rolebinding NAME --clusterrole=NAME|--role=NAME [--user=username] [--group=groupname] [--serviceaccount=namespace:serviceaccountname] 


#kubectl create rolebinding ${APP_NAME} --role=${APP_NAME} --user=${APP_NAME} --group=${APP_NAME} --serviceaccount=${APP_NS}:default

: '
printf "\n\n%s\n" " ------ RBAC: Role-based access control " 
printf "\n\n%s\n" "       ------ RBAC: roles " 
kubectl get roles
printf "\n\n%s\n" "       ------ RBAC: rolebindings " 
kubectl get rolebindings
printf "\n\n%s\n" "       ------ RBAC: clusterroles " 
kubectl get clusterroles
printf "\n\n%s\n" "       ------ RBAC: clusterrolebindings " 
kubectl get clusterrolebindings
printf "\n\n%s\n" "       ------ RBAC: clusterrole system:node " 
kubectl get clusterrole system:node -o yaml


printf "\n\n%s\n" " ------ ABAC: Attribute-based access control " 
 # system:serviceaccount:<namespace>:default



# kubectl create rolebinding admin --clusterrole=admin --user=krishnamanchikalapudi --group=k8sgroup   system:basic-user
'

printf "\n\n%s\n" " ------ exposing app"
: ' 
--port: The port that the service should serve on. 
--target-port: Name or number for the port on the container that the service should direct traffic to. 
--type: Type for this service: ClusterIP, NodePort, LoadBalancer, or ExternalName. Default is ClusterIP.
'
if $isNsExists ; then   # isNsExists=true
    kubectl expose --namespace=${APP_NS} --name=${APP_NAME} --type="NodePort" --port=${APP_PORT} --target-port=${APP_PORT} --allow-missing-template-keys=true
else   # isNsExists=false
    kubectl expose --name=${APP_NAME} --type="NodePort" --port=${APP_PORT} --target-port=${APP_PORT} --allow-missing-template-keys=true
fi

printf "\n\n%s\n" " ------ Details of all the Service objects deployed " 
kubectl describe services/${APP_NAME}


# kubectl create clusterrolebinding add-on-cluster-admin --clusterrole=cluster-admin --serviceaccount=kube-system:default

HOST_IP=`minikube ip`
sleep 15
printf "\n\n%s\n" " ------ opening K8S dashboard "
minikube dashboard &

sleep 5

curl http://${HOST_IP}:${APP_PORT}/v1/

sleep 2
printf "\n%s\n\n" "----------- [START] DEPLOY: ${DATE_TIME} "
exit 0
