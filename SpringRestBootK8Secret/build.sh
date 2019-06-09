#!/bin/bash

. ./deployment/config.sh

printf "\n\n%s\n" "----------- [START] Build: ${DATE_TIME} "

rm -rf build 

gradle clean build

printf "\n%s\n" "--------------------"

ls -1 -lrt build/libs/*

pwd
printf "\n%s\n\n" "----------- [START] Build: ${DATE_TIME} "