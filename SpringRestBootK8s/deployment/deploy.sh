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
printf "\n%s\n\n" "----------- [START] DEPLOY: ${DATE_TIME} "
exit 0
