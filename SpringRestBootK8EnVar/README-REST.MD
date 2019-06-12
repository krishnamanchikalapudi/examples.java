# Spring Rest Service


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
Welcome to Spring Rest + Boot + Vault example
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
- Console: output
```
content-type: application/json
Vault info::
----------------------------------------
Configuration properties
                db.url=jdbc:mysql://localhost:3306/springbootdevdb 
                db.username=HelloDevSecretUser
                db.password=HelloDevSecretPwd
----------------------------------------
```