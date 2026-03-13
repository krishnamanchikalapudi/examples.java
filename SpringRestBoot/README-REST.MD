# Spring Rest Service

### GET url: http://localhost:8080/v1/123
- Sample Request
```
curl -H "content-type:text/toon" http://localhost:8080/v1/123
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

### GET url: http://localhost:8080/v1/123
- Sample Request
```
curl -H "content-type:application/json" http://localhost:8080/v1/123
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

### GET url: http://localhost:8080/v1/123
- Sample Request
```
curl -H "content-type:application/xml" http://localhost:8080/v1/123
```
- Response: header
```
	status: 200
	CurrentTime: 
	
```
- Response: body
```
<Person>
    <id>123</id>
    <firstName>Krishna</firstName>
</Person>
`````