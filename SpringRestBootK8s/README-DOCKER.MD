# Docker
- [Commands](https://docs.docker.com/engine/reference/commandline/container_ls/#related-commands)


### build docker image
`````
gradle clean build
`````


### Building the Docker image & patter 'docker build -t <YOUR_USERNAME>/<APP_NAME> .'
`````
docker image build -t springrestbootk8:latest .
`````

output
`````
Krishnas-MacBo:SpringRestBootK8s krishnamanchikalapudi$ docker image build -t springrestbootk8:latest .
Sending build context to Docker daemon  36.99MB
Step 1/7 : FROM openjdk:8-jdk-alpine
 ---> a3562aa0b991
Step 2/7 : MAINTAINER Krishna.Manchikalapudi@gmail.com
 ---> Using cache
 ---> 7b9bca25a6bf
Step 3/7 : ENV WAR_FILE springrestbootk8.war
 ---> Using cache
 ---> 0e7d6affef17
Step 4/7 : ENV BIN_WAR_FILE build/libs/${WAR_FILE}
 ---> Using cache
 ---> 974eaac29f42
Step 5/7 : ENV TMP_LOC_FILE /tmp/${WAR_FILE}
 ---> Using cache
 ---> ee4731588e78
Step 6/7 : COPY ${BIN_WAR_FILE} ${TMP_LOC_FILE}
 ---> 6f2cf664af6f
Step 7/7 : CMD java -jar ${TMP_LOC_FILE}
 ---> Running in 94ddeaebbb33
Removing intermediate container 94ddeaebbb33
 ---> 16ebc610842e
Successfully built 16ebc610842e
Successfully tagged springrestbootk8:latest
`````


### Delete or Remove images
`````
docker image rm springrestbootk8
`````

### Docker login at https://hub.docker.com/
`````
docker login
`````

### Run a command in a new container & pattern  'docker container run -p 8888:5000 APP_NAME:latest
`````
docker container run -d -p 7080:8080 springrestbootk8:latest
`````

### List container command & pattern  'docker container ls -a'
`````
docker container ls -a | grep springrestbootk8
`````


### Stop containers & pattern  'docker container stop CONTAINER_ID'
`````
container_id=`docker container ls -a | grep springrestbootk8 | awk '{print $1}'`
docker container stop $container_id && docker system prune -f
`````

### tail log
`````
container_id=`docker container ls -a | grep springrestbootk8 | awk '{print $1}'`
docker logs -f $container_id
`````


docker container stop 84ef0396add2 && docker system prune -f


### Push an image or a repository to a https://hub.docker.com & pattern 'docker push <hub-user>/<repo-name>:<tag>'
`````
docker tag springrestbootk8 krishnamanchikalapudi/springrestbootk8
docker push krishnamanchikalapudi/springrestbootk8
`````


# Service details
`````
curl http://localhost:7080/v1/
`````
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
