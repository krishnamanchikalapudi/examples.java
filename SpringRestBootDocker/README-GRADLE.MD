# Gradle script

### Eclipse configuration
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
java -jar build/libs/springrestbootdocker.war --server.port=8080 &

`````

### check process
`````
ps -ef | grep springrestbootdocker.war | awk '{print $2}'
netstat -lntp | grep 8080
`````

# Service details

### GET url: http://localhost:8080/v1/
- Request: header
```
```
- Request: body
```
```
- Response: header
```
```
- Response: body
```
Welcome to Spring Rest + Boot example
```

### GET url: http://localhost:8080/v1/123
- Request: header
```
content-type:application/json
```
- Request: body
```
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
    "firstName": "Krishna"
}
```
