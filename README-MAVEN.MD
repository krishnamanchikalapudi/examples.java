
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

### kill process
`````
kill $(lsof -t -i :8080) &
`````