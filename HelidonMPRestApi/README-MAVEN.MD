
### Clean Build
`````
mvn clean package
`````

### execute via java command
`````
java -jar target/helidonmprest.jar &
`````

### check process
`````
ps -ef | grep helidonmprest.jar | awk '{print $2}'
`````

### kill process
`````
kill $(lsof -t -i :8080) &
`````