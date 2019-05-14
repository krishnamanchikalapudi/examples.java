# S2I
- [Download](https://github.com/openshift/source-to-image/releases/latest)

### build docker image via s2i command
`````
export baseImage="openjdk"
s2i build . $baseImage springrestboots2i:latest
`````


### Delete or Remove images
`````
docker image rm springrestboots2i
`````

### Run a command in a new container & pattern  'docker container run -p 8888:5000 APP_NAME:latest
`````
docker container run -d -p 7080:8080 springrestboots2i:latest
`````

### List container command & pattern  'docker container ls -a'
`````
docker container ls -a
`````


### Stop containers & pattern  'docker container stop CONTAINER_ID'
`````
docker container stop eb34bce6bfd0
`````


### Push an image or a repository to a https://hub.docker.com & pattern 'docker push <hub-user>/<repo-name>:<tag>'
`````
docker tag springrestboots2i iamkittu/springrestboots2i
docker push iamkittu//springrestboots2i

`````


# Service details

### GET url: http://localhost:7080/v1/
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

### GET url: http://localhost:7080/v1/123
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
