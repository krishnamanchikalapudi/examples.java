#!/bin/bash

printf "\n\n\n%s\n\n\n" "----------- ----------- ----------- ----------- -----------  "

DATE=`date +%Y-%m-%d`
DATE_TIME=`date '+%Y-%m-%d %H:%M:%S'`

export APP_NAME='springrestbootk8secret'
export APP_YAML=${APP_NAME}.yml
export APP_NS=${APP_NAME}-ns
export DOCKER_IMAGE='docker.io/krishnamanchikalapudi/springrestbootk8secret:latest'
export APP_PORT='8080'

printf "\n%s\n" "----------- [minikube] INFO:  "
minikube version
printf "\n%s\n" "----------- [kubectl] INFO:  "
kubectl version

printf "\n\n%s\n" 

: '
if $isNsExists ; then  # isNsExists=true
    printf "\n\n%s\n%s\n" "-- [var] isNsExists: ${isNsExists}" "-- Namespace exists = true "
else  # false
    printf "\n\n%s\n%s\n" "-- [var] isNsExists: ${isNsExists}" "-- Namespace exists: false "
fi
'

