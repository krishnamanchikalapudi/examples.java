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

### execute via java command
`````
java -jar build/libs/springrestbootk8secret.jar --server.port=8080 --db.url=jdbc:mysql://localhost:3306/springbootdb --db.username=HelloName --db.password=HelloPassword &
`````

### mac - check process
`````
lsof -nP -iTCP:8080 | awk '{print $2}'
`````

### linux - check process
`````
ps -ef | grep springrestbootk8secret.jar | awk '{print $2}'
netstat -lntp | grep 8080
`````

## Unit-Test
### GET url: http://localhost:8080/v1/
- Terminal
```
curl localhost:8080/v1/
```
- Response: body
```
Welcome to Spring Rest + Boot + K8 Secret example 
 
----------------------------------------
Configuration properties
 		 db.url = jdbc:mysql://localhost:3306/springbootdb
 		 db.username = HelloName
 		 db.password = HelloPassword
----------------------------------------
```

### GET url: http://localhost:8080/v1/123
- Terminal
```
curl -H "content-type: application/json" localhost:8080/v1/123
```
- Response: header
```
	status: 200
	CurrentTime: 
	
```
- Response: body
```
{
    "id": "123",
    "firstName": "Krishna",
    "otherInfo": "\n----------------------------------------\nConfiguration properties\n \t\t db.url = jdbc:mysql://localhost:3306/springbootdb\n \t\t db.username = HelloName\n \t\t db.password = HelloPassword\n----------------------------------------\n"
}
```