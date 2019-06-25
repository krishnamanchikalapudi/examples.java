#!/bin/bash

printf "\n\n\n%s\n\n\n" "----------- ----------- ----------- ----------- -----------  "

DATE=`date +%Y-%m-%d`
DATE_TIME=`date '+%Y-%m-%d %H:%M:%S'`

export APP_NAME='springrestbootjwt'
export APP_YAML=${APP_NAME}.yml
export APP_NS=${APP_NAME}-ns
export DOCKER_IMAGE='docker.io/krishnamanchikalapudi/springrestbootjwt:latest'
export APP_PORT='8080'
export APP_URL="localhost:${APP_PORT}/v1/"
export JWT_KEY='eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJLcmlzaG5hIiwidXNlcklkIjoiMTIzIiwicm9sZSI6ImFkbWluIn0.lCOQ-Q0lqxGrSqNMLLAVY7RG94klRoASbBhtddY1_F3fXoAU4-AxjQ1YUz6zJ_WK2dIB3YoHUCOcGSA2erUXkQ'

printf "\n%s\n" "----------- [Java] INFO:  "
java -version
printf "\n%s\n" "----------- [Gradle] INFO:  "
gradle -v
printf "\n%s\n" "----------- [minikube] INFO:  "
minikube version
: '
printf "\n%s\n" "----------- [kubectl] INFO:  "
kubectl version
'

printf "\n\n%s\n" 

: '
if $isNsExists ; then  # isNsExists=true
    printf "\n\n%s\n%s\n" "-- [var] isNsExists: ${isNsExists}" "-- Namespace exists = true "
else  # false
    printf "\n\n%s\n%s\n" "-- [var] isNsExists: ${isNsExists}" "-- Namespace exists: false "
fi
'

