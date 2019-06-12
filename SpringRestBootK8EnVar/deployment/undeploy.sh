#!/bin/bash

. ./config.sh

printf "\n%s\n\n" "----------- [START] UNDEPLOY: ${DATE_TIME} "


printf "\n\n%s\n" " ------ deleting service, pod, namespace "
kubectl delete -f ${APP_YAML} --ignore-not-found=true


sleep 15
printf "\n\n%s\n" " ------ opening K8S dashboard "
minikube dashboard &

sleep 2
printf "\n%s\n\n" "----------- [DONE] UNDEPLOY: ${DATE_TIME} "
exit 0