#!/bin/bash

. ./config.sh

printf "\n%s\n\n" "----------- [START] UNDEPLOY-JAVA: ${DATE_TIME} "


APP_PID=`ps -ef | grep ${APP_NAME}.jar | awk '{print $2}'`
printf "\n\t%s\n" "----------- Process id: ${APP_PID} "

kill -9 ${APP_PID}  &

kill $(lsof -t -i :8080) &


sleep 2
printf "\n%s\n\n" "----------- [DONE] UNDEPLOY-JAVA: ${DATE_TIME} "
exit 0

