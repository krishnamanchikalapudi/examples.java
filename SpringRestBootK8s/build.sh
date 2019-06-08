#!/bin/bash

. ./deployment/config.sh
printf "\n\n%s\n" "----------- Start: ${DATE_TIME} "


rm -rf build 

gradle clean build

printf "\n%s\n" "--------------------"

ls -lrt build/libs/

printf "\n%s\n" "--------------------"
docker image build -t ${APP_NAME}:latest .
docker tag ${APP_NAME} krishnamanchikalapudi/${APP_NAME}
docker push krishnamanchikalapudi/${APP_NAME}

printf "\n%s\n" "--------------------"

cd deployment
pwd
printf "\n%s\n\n" "----------- DONE: ${DATE_TIME} "