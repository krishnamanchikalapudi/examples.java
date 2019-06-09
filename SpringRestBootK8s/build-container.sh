#!/bin/bash

. ./build.sh

printf "\n\n%s\n" "----------- [START] Build - Container: ${DATE_TIME} "

docker image build -t ${APP_NAME}:latest .
docker tag ${APP_NAME} krishnamanchikalapudi/${APP_NAME}
docker push krishnamanchikalapudi/${APP_NAME}

printf "\n%s\n" "--------------------"
pwd
printf "\n%s\n\n" "----------- [DONE] Build - Container: ${DATE_TIME} "