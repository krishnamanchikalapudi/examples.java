#!/bin/bash

DATE=`date +%Y-%m-%d`
DATE_TIME=`date '+%Y-%m-%d %H:%M:%S'`

appName='springrestbootk8s'

minikube version
kubectl version

printf "\n\n%s\n" " ------ deleting pods, service, etc "
kubectl delete -f kustomization.yaml 

printf "\n\n%s\n" " ------ Delete K8S namespace "
kubectl delete namespace ${appName}-ns

sleep 15
printf "\n\n%s\n" " ------ opening K8S dashboard "
minikube dashboard &

printf "\n\n"