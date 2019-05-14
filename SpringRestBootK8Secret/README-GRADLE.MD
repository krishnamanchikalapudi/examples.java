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
java -jar build/libs/springrestbootk8secret.war --server.port=8080 &
`````

### linux - check process
`````
ps -ef | grep springrestbootk8secret.war | awk '{print $2}'
netstat -lntp | grep 8080
`````

### mac - check process
`````
lsof -nP -iTCP:8200
`````