#!/bin/bash

. ./config.sh
printf "\n\n%s\n" "----------- Start: ${DATE_TIME} "

cd ..
gradle clean build

printf "\n%s\n" "--------------------"

ls -lrt build/libs/

printf "\n%s\n" "--------------------"
docker image build -t springrestbootk8:latest .
docker tag springrestbootk8 krishnamanchikalapudi/springrestbootk8
docker push krishnamanchikalapudi/springrestbootk8

printf "\n%s\n" "--------------------"

cd deployment
pwd
printf "\n%s\n\n" "----------- DONE: ${DATE_TIME} "