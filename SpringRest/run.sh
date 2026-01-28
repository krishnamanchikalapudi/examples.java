#!/bin/bash
arg=${1:-"START"}

precheck(){
    java -version
    gradle -version
    if [ $? -ne 0 ]; then
        echo "Java or Gradle is not installed"
        exit 1
    fi
}
build(){
    echo "Building server..."
    gradle clean build
}
start(){
    echo "Starting server..."
    gradle bootRun & 
}
test() {
    curl -H "Accept: application/json" http://localhost:8080/person/123 

    echo " " && sleep 2 && echo " " 

    curl -X GET "http://localhost:8080/person/1" -H "accept: application/xml"
}

stop(){
    echo "Stopping server..."
    kill $(lsof -t -i:8080)
    gradle bootStop
}

restart(){
    stop
    sleep 2
    build
    sleep 2
    start
    sleep 5
    test
}
jarStart(){
    rm -rf build 
    sleep 2
    build
    sleep 2
    echo "Starting server from jar..."
    java -jar build/libs/springrest-0.0.1-SNAPSHOT.jar & 
}
jarStop(){
    echo "Stopping server from jar..."
    kill $(lsof -t -i:8080) 
}
if [[ -n $arg ]] ; then
    arg_len=${#arg}
    # uppercase the argument
    arg=$(echo ${arg} | tr [a-z] [A-Z] | xargs)
    echo "User Action: ${arg}, and arg length: ${arg_len}"

    case $arg in
        "BUILD")
            build
            ;;
        "START")
            restart
            ;;
        "STOP")
            stop
            ;;
        "TEST")
            test
            ;;
        "JAR")
            jarStop
            sleep 2
            jarStart
            sleep 5
            test
            sleep 2
            jarStop
            ;;
    esac
fi