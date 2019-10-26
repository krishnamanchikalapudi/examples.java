### Eclipse
`````
mvn eclipse:clean eclipse:eclipse
`````

### Clean Build
`````
mvn clean package
`````

### boot run
`````
mvn spring-boot:run
`````


### kill process
`````
kill -9 $(lsof -t -i :8080) &
`````