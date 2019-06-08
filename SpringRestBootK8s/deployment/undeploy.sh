#!/bin/bash

. ./config.sh

printf "\n%s\n\n" "----------- [START] UNDEPLOY: ${DATE_TIME} "

printf "\n\n%s\n" " ------ deleting pods, service, etc "
kubectl delete -f ${APP_YAML} --ignore-not-found=true

# printf "\n\n%s\n" " ------ Delete K8S namespace "
# kubectl delete namespace ${APP_NS}  --ignore-not-found=true



sleep 15
printf "\n\n%s\n" " ------ opening K8S dashboard "
minikube dashboard &

sleep 2
printf "\n%s\n\n" "----------- [DONE] UNDEPLOY: ${DATE_TIME} "
exit 0