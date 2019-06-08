### Eclipse
`````
gradle cleanEclipseClasspath eclipse
`````

### Clean Build
`````
gradle clean build
`````


### Clean Build skipTests
`````
gradle clean build -x test --stacktrace --warning-mode all
`````

### boot run
`````
gradle clean bootRun -Dserver.port=8080
`````

### execute via war
`````
java -jar build/libs/springrestbootk8.jar --server.port=7080 &
`````

### verify service
`````
curl http://localhost:7080/v1/
`````


### linux - check process
`````
ps -ef | grep springrestbootk8 | awk '{print $2}'
netstat -lntp | grep 7080
`````

### mac - check process
`````
lsof -nP -iTCP:7080
`````