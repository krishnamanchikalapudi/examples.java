#!/bin/bash

. ./config.sh

printf "\n%s\n\n" "----------- [START] BUILD-JAVA: ${DATE_TIME} "

cd ../

gradle clean build -x test


cd deployment
printf "\n%s\n\n" "----------- [DONE] BUILD-JAVA: ${DATE_TIME} "

