# Docker
- [Commands](https://docs.docker.com/engine/reference/commandline/container_ls/#related-commands)


### Building the Docker image & patter 'docker build -t <YOUR_USERNAME>/<APP_NAME> .'
`````
docker image build -t springrestbootk8secret:latest .
`````

output
`````
Sending build context to Docker daemon  37.11MB
Step 1/7 : FROM openjdk:8-jdk-alpine
 ---> a3562aa0b991
Step 2/7 : MAINTAINER Krishna.Manchikalapudi@gmail.com
 ---> Using cache
 ---> 7b9bca25a6bf
Step 3/7 : ENV JAR_FILE springrestbootk8secret.jar
 ---> Using cache
 ---> 3ca30bb59428
Step 4/7 : ENV BIN_JAR_FILE build/libs/${JAR_FILE}
 ---> Using cache
 ---> 72910f190237
Step 5/7 : ENV TMP_LOC_FILE /tmp/${JAR_FILE}
 ---> Using cache
 ---> 9fd9fa6ce4fa
Step 6/7 : COPY ${BIN_JAR_FILE} ${TMP_LOC_FILE}
 ---> 115ec46bd070
Step 7/7 : CMD java -jar ${TMP_LOC_FILE}
 ---> Running in 739d2934b5bb
Removing intermediate container 739d2934b5bb
 ---> e723f56446b5
Successfully built e723f56446b5
`````


### Delete or Remove images
`````
docker image rm springrestbootk8secret
`````

### Run a command in a new container & pattern  'docker container run -p 8888:5000 APP_NAME:latest
`````
docker container run -d -e db.url=jdbc:mysql://localhost:3306/springDocdb -e db.username=HelloDocName -e db.password=HelloDocPassword -p 8080:8080 springrestbootk8secret:latest &
`````

### List container command & pattern  'docker container ls -a'
`````
docker container ls -a
`````

### tail tail continuously
`````
container_id=`docker container ls -a | grep springrestbootk8secret | awk '{print $1}'`
docker logs --follow $container_id &
`````

### Stop containers & pattern  'docker container stop CONTAINER_ID'
`````
container_id=`docker container ls -a | grep springrestbootk8secret | awk '{print $1}'`
docker container stop $container_id && docker system prune -f
`````


### Push an image or a repository to a https://hub.docker.com & pattern 'docker push <hub-user>/<repo-name>:<tag>'
`````
docker tag springrestbootk8secret krishnamanchikalapudi/springrestbootk8secret
docker push krishnamanchikalapudi/springrestbootk8secret
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