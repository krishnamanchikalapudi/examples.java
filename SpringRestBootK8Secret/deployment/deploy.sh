#!/bin/bash

. ./config.sh

printf "\n%s\n\n" "----------- [START] DEPLOY: ${DATE_TIME} "


printf "\n\n%s\n" " ------ Deploying ${APP_YAML} "
kubectl apply -f ${APP_YAML}


printf "\n\n%s\n" " ------ list of all the deployed objects"
kubectl get deployment --namespace=${APP_NS} --ignore-not-found=true



sleep 15
printf "\n\n%s\n" " ------ opening K8S dashboard "
minikube dashboard &


sleep 2

M7_IP=`minikube ip`

NODE_PORT=$(kubectl get services/${APP_NAME}-service --namespace=${APP_NS} -o go-template='{{(index .spec.ports 0).nodePort}}')
printf "\n\n%s\n%s\n\n" " -- MiniKube IP: ${M7_IP} " " -- NODE PORT: ${NODE_PORT} "

sleep 5 

K8S_URL="${M7_IP}:${NODE_PORT}/v1/"
printf "\n%s\n\n" "--- content-type: application/json, URL: ${K8S_URL} "
curl ${K8S_URL}

printf "\n%s\n\n" "--- content-type: application/json, URL: ${K8S_URL}123"
curl -H "content-type: application/json" ${K8S_URL}123


printf "\n%s\n\n" "----------- [DONE] DEPLOY: ${DATE_TIME} "
exit 0
