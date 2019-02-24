
### Clean Build
`````
mvn clean install -DskipTests=true
`````


### Display the project dependencies.
`````
mvn dependency:tree
`````

### boot run
`````
gradle bootRun -Dserver.port=8080 &
`````

### debug boot run
`````
gradle bootRun -Dserver.port=8080 -Dagentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=6666 &
`````

### execute via java command
`````
java -jar target/springrestboot-1.0.0-SNAPSHOT.jar  --server.port=8080 &
`````

mvn package && java -jar target/gs-spring-boot-0.1.0.jar

### check process
`````
ps -ef | grep springrestboot.war | awk '{print $2}'
`````

### kill process
`````
kill $(lsof -t -i :8080) &
`````