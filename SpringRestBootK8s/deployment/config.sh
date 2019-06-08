#!/bin/bash

DATE=`date +%Y-%m-%d`
DATE_TIME=`date '+%Y-%m-%d %H:%M:%S'`

export isNsExists=true
export isContainerImageExists=false
: '
varNamespace=$1
if [ ! -z "$varNamespace" ]; then
    isNsExists=true
fi
varContainerImageExists=$2
if [ ! -z "$varContainerImageExists" ]; then
    isContainerImageExists=true
fi
'

export APP_NAME='springrestbootk8'
export APP_NS=${APP_NAME}-ns
export DOCKER_IMAGE='docker.io/krishnamanchikalapudi/springrestbootk8:latest'
export APP_PORT='8080'


kubectl version

printf "\n\n%s\n" 

if $isNsExists ; then  # isNsExists=true
    printf "\n\n%s\n%s\n" "-- [var] isNsExists: ${isNsExists}" "-- Namespace exists = true "
else  # false
    printf "\n\n%s\n%s\n" "-- [var] isNsExists: ${isNsExists}" "-- Namespace exists: false "
fi


