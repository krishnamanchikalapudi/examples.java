
### Clean Build
`````
mvn clean install -DskipTests=true
`````

### Cert issue to Build
`````
mvn clean install -DskipTests=true -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true 
`````

### Display the project dependencies.
`````
mvn dependency:tree
`````

### debug boot run
`````
mvn package && java -jar target/springrestboot-1.0.0-SNAPSHOT.jar
`````

### execute via java command
`````
java -jar target/springrestboot-1.0.0-SNAPSHOT.jar  --server.port=8080 &
`````


### check process
`````
ps -ef | grep springrestboot.war | awk '{print $2}'
`````

### kill process
`````
kill $(lsof -t -i :8080) &
`````