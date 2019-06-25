#!/bin/bash

. ./build.sh


printf "\n%s\n\n" "----------- [START] DEPLOY-JAVA: ${DATE_TIME} "

java -jar ../build/libs/${APP_NAME}.jar --server.port=${APP_PORT} &

sleep 30



printf "\n%s\n\n" "--- content-type: application/json, URL: ${APP_URL} "
curl -H "Authorization:Bearer ${JWT_KEY}" ${APP_URL}


printf "\n%s\n\n" "--- content-type: application/json, URL: ${K8S_URL}123"
curl -H "content-type: application/json" -H "Authorization:Bearer ${JWT_KEY}" ${APP_URL}123


printf "\n\n%s\n\n" "----------- [DONE] DEPLOY-JAVA: ${DATE_TIME} "

