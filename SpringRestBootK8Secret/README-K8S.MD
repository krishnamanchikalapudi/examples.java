# Docker
- [Commands](https://kubernetes.io/docs/reference/kubectl/cheatsheet/)


### Deploy
`````
./deployment/deploy.sh
`````

output
`````

`````

### Remove image
`````
./deployment/undeploy.sh
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
 		 db.url = jdbc:mysql://localhost:3306/springDocdb
 		 db.username = HelloDocName
 		 db.password = HelloDocPassword
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
    "otherInfo":"\n----------------------------------------\nConfiguration properties\n \t\t db.url = jdbc:mysql://localhost:3306/springDocdb\n \t\t db.username = HelloDocName\n \t\t db.password = HelloDocPassword\n----------------------------------------\n"
}
```