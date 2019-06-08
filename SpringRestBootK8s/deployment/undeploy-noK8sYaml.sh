#!/bin/bash
. ./config.sh

printf "\n%s\n\n" "----------- [START] UNDEPLOY: ${DATE_TIME} "
if $isNsExists ; then  # true
    printf "\n\n%s\n" " ------ Delete K8S namespace: ${APP_NS}"
    kubectl delete namespace ${APP_NS} --force --ignore-not-found=true 
else # false
    printf "\n\n%s\n" " ------ Delete K8S app: ${APP_NAME} "
    kubectl delete pods,services,deployments ${APP_NAME} --force --ignore-not-found=true 
    # kubectl delete pods,services,deployments springrestbootk8 --force --ignore-not-found=true 
fi

sleep 15
kubectl get pods | grep ${APP_NAME} | awk '{print $1}'
# kubectl get pods | grep springrestbootk8 | awk '{print $1}'

: '
  POD_ID=`kubectl get pods | grep ${APP_NAME} | awk '{print $1}'`
    kubectl delete pods $POD_ID &

printf "\n\n%s\n" " ------ Delete App pods namespace "
kubectl delete pods,services,deployment -l app=${APP_NAME}

printf "\n\n%s\n" " ------ Delete K8S namespace "
'
  
sleep 5
printf "\n\n%s\n" " ------ opening K8S dashboard "
minikube dashboard &

sleep 5
printf "\n%s\n\n" "----------- [DONE] UNDEPLOY: ${DATE_TIME} "
exit 0

