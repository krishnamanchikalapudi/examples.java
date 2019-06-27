#!/bin/bash

. ./build.sh


printf "\n%s\n\n" "----------- [START] DEPLOY-JAVA: ${DATE_TIME} "

java -jar ../build/libs/${APP_NAME}.jar --server.port=${APP_PORT} &

sleep 40


printf "\n%s\n\n" "--- content-type: application/json, URL: ${APP_URL_V1} "
curl ${APP_URL_V1}; echo 


printf "\n%s\n\n" "--- content-type: application/json, URL: ${APP_URL_V1} "
curl -H "Authorization:Bearer ${JWT_KEY}" ${APP_URL_V1}; echo 


printf "\n%s\n\n" "--- NO Authorization:Bearer, URL: ${APP_URL}liveStatus "
curl -H "content-type: application/json" ${APP_URL}liveStatus; echo 


printf "\n%s\n\n" "--- NO Authorization:Bearer, URL: ${APP_URL_V1}liveStatus "
curl -H "content-type: application/json" -H "Authorization:Bearer ${JWT_KEY}" ${APP_URL_V1}liveStatus; echo 


printf "\n%s\n\n" "--- content-type: application/json, URL: ${APP_URL_V1}123"
curl -H "content-type: application/json" -H "Authorization:Bearer ${JWT_KEY}" ${APP_URL_V1}123; echo 

printf "\n%s\n\n" "--- content-type: application/xml, URL: ${APP_URL_V1}123"
curl -H "Accept: application/xml" -H "content-type: application/xml" -H "Authorization:Bearer ${JWT_KEY}" ${APP_URL_V1}123; echo 


printf "\n\n%s\n\n" "----------- [DONE] DEPLOY-JAVA: ${DATE_TIME} "

