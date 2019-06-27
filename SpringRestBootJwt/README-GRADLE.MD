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
gradle bootRun -Dserver.port=8080 &
`````

### debug boot run
`````
gradle bootRun -Dserver.port=8080 -Dagentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=6666 &
`````

### execute via war
`````
java -jar build/libs/springrestbootjwt.jar --server.port=8080 &
`````

### check process
`````
ps -ef | grep springrestbootjwt.jar | awk '{print $2}'
`````

### kill process
`````
kill -9 $(lsof -t -i :8080) &
`````