# Docker
- [Commands](https://docs.docker.com/engine/reference/commandline/container_ls/#related-commands)


### build docker image
`````
gradle clean build docker -x test
`````


### Building the Docker image & patter 'docker build -t <YOUR_USERNAME>/<APP_NAME> .'
`````
docker image build -t springrestbootdocker:latest .
`````

output
`````
Sending build context to Docker daemon  45.48MB
Step 1/8 : FROM java:8
 ---> d23bdf5b1b1b
Step 2/8 : MAINTAINER Krishna.Manchikalapudi@gmail.com
 ---> Running in ec9bd1c5ef79
Removing intermediate container ec9bd1c5ef79
 ---> e4d59f0e8703
Step 3/8 : ENV WAR_FILE springrestbootdocker.war
 ---> Running in 42c2f872cbf0
Removing intermediate container 42c2f872cbf0
 ---> 33681f3cf885
Step 4/8 : ENV BIN_WAR_FILE build/libs/${WAR_FILE}
 ---> Running in 5bdda4c191d2
Removing intermediate container 5bdda4c191d2
 ---> 16d90d68a6ed
Step 5/8 : ENV TMP_LOC_FILE /tmp/${WAR_FILE}
 ---> Running in dc85540a202b
Removing intermediate container dc85540a202b
 ---> b9a66fbfc12e
Step 6/8 : COPY ${BIN_WAR_FILE} ${TMP_LOC_FILE}
 ---> e5c9331f9f68
Step 7/8 : EXPOSE 8080
 ---> Running in fd1a5f308300
Removing intermediate container fd1a5f308300
 ---> c7f8d295f74a
Step 8/8 : CMD java -jar ${TMP_LOC_FILE}
 ---> Running in e2f2417c0a89
Removing intermediate container e2f2417c0a89
 ---> dcaabaf08f74
Successfully built dcaabaf08f74
Successfully tagged springrestbootdocker:latest
`````


### Delete or Remove images
`````
docker image rm springrestbootdocker
`````

### Docker login at https://hub.docker.com/
`````
docker login
`````

### Run a command in a new container & pattern  'docker container run -p 8888:5000 APP_NAME:latest
`````
docker container run -d -p 7080:8080 springrestbootdocker:latest
`````

### List container command & pattern  'docker container ls -a'
`````
docker container ls -a
`````


### Stop containers & pattern  'docker container stop CONTAINER_ID'
`````
container_id=`docker container ls -a | grep springrestbootdocker | awk '{print $1}'`
docker container stop $container_id && docker system prune -f
`````


### Push an image or a repository to a https://hub.docker.com & pattern 'docker push <hub-user>/<repo-name>:<tag>'
`````
docker tag springrestbootdocker iamkittu/springrestbootdocker
docker push iamkittu/springrestbootdocker

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
