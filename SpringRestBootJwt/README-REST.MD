# Spring Rest Service


### GET url: http://localhost:8080/v1/
```
```
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


### GET url: http://localhost:8080/v1/
```
curl http://localhost:8080/v1/; echo 

```
- Request: header
```
```
- Request: body
```
```
- Response: header
```
	status: 401 Unauthorized
```
- Response: body
```
{"timestamp":"2019-06-27T00:58:07.188+0000","status":401,"error":"Unauthorized","message":"Unauthorized","path":"/v1/"}
```


### GET url: http://localhost:8080/liveStatus
```
curl -H "content-type: application/json" http://localhost:8080/liveStatus; echo
```
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
	"live":true,
	"liveAsOf":"2019-06-27T01:00:08.740+0000"
}
```



### GET url: http://localhost:8080/v1/liveStatus
```
curl -H "content-type: application/json" http://localhost:8080/v1/liveStatus; echo
```
- Request: header
```
content-type:application/json
```
- Request: body
```
```
- Response: header
```
	status: 500
	
```
- Response: body
```
{
    "timestamp": "2019-06-27T01:06:38.700+0000",
    "status": 500,
    "error": "Internal Server Error",
    "message": "For input string: \"liveStatus\"",
    "path": "/v1/liveStatus"
}
```

### GET url: http://localhost:8080/v1/123
```
curl -H "content-type: application/json" -H "Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJLcmlzaG5hIiwidXNlcklkIjoiMTIzIiwicm9sZSI6ImFkbWluIn0.lCOQ-Q0lqxGrSqNMLLAVY7RG94klRoASbBhtddY1_F3fXoAU4-AxjQ1YUz6zJ_WK2dIB3YoHUCOcGSA2erUXkQ" http://localhost:8080/v1/123
```
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
```
curl -H "Accept: application/xml" -H "content-type: application/xml" -H "Authorization:Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJLcmlzaG5hIiwidXNlcklkIjoiMTIzIiwicm9sZSI6ImFkbWluIn0.lCOQ-Q0lqxGrSqNMLLAVY7RG94klRoASbBhtddY1_F3fXoAU4-AxjQ1YUz6zJ_WK2dIB3YoHUCOcGSA2erUXkQ" http://localhost:8080/v1/123
```
- Request: header
```
Accept:application/xml
Content-Type:application/xml
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
<Person>
    <id>123</id>
    <firstName>Krishna</firstName>
</Person>
`````