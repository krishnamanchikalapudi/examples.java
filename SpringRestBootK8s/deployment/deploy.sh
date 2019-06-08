#!/bin/bash

DATE=`date +%Y-%m-%d`
DATE_TIME=`date '+%Y-%m-%d %H:%M:%S'`

appName='springrestbootk8s'


kubectl version

printf "\n\n%s\n" " ------ Create K8S namespace "
kubectl create namespace ${appName}-ns

printf "\n\n%s\n" " ------ Deploying kustomization.yaml "
kubectl apply -f kustomization.yaml 


printf "\n\n%s\n" " ------ list of all the deployed objects"
kubectl get deployment 
printf "\n\n%s\n" " ------ Details of individual deployments "
kubectl describe deployment $appName
printf "\n\n%s\n" " ------ Details of all the Service objects deployed " 
kubectl describe svc ${appName}-svc
printf "\n\n%s\n" " ------ Status of the Ingress rules  " 
kubectl get ing ${appName}-ingress

sleep 15
printf "\n\n%s\n" " ------ opening K8S dashboard "
minikube dashboard &



kubectl -n s delete --all  

printf "\n\n"


