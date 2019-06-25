# Spring Rest Service


### GET url: http://localhost:8080/v1/
- Request: header
```
Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJLcmlzaG5hIiwidXNlcklkIjoiMTIzIiwicm9sZSI6ImFkbWluIn0.lCOQ-Q0lqxGrSqNMLLAVY7RG94klRoASbBhtddY1_F3fXoAU4-AxjQ1YUz6zJ_WK2dIB3YoHUCOcGSA2erUXkQ
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
Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJLcmlzaG5hIiwidXNlcklkIjoiMTIzIiwicm9sZSI6ImFkbWluIn0.lCOQ-Q0lqxGrSqNMLLAVY7RG94klRoASbBhtddY1_F3fXoAU4-AxjQ1YUz6zJ_WK2dIB3YoHUCOcGSA2erUXkQ
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

### GET url: http://localhost:8080/v1/123
- Request: header
```
Accept:application/xml
Content-Type:application/xml
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
<Person>
    <id>123</id>
    <firstName>Krishna</firstName>
</Person>
`````