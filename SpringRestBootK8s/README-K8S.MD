# kubectl setup

1. Download kubectl and install from https://github.com/kubernetes/kubectl


### Docker image 
2. Create docker image
`````
docker image build -t springrestbootk8s:latest .
`````

output
`````
Krishnas-MBP-2:SpringRestBootK8s krishnamanchikalapudi$ docker image build -t springrestbootk8s:latest .
Sending build context to Docker daemon  36.89MB
Step 1/7 : FROM openjdk:8-jdk-alpine
 ---> 2cfb1dc1f0c8
Step 2/7 : MAINTAINER Krishna.Manchikalapudi@gmail.com
 ---> Running in 9435f1503e50
Removing intermediate container 9435f1503e50
 ---> 1cfb3e1eb448
Step 3/7 : ENV WAR_FILE springrestbootk8s.war
 ---> Running in cf7fa28a4b57
Removing intermediate container cf7fa28a4b57
 ---> d78cf5d68952
Step 4/7 : ENV BIN_WAR_FILE build/libs/${WAR_FILE}
 ---> Running in fd464b6982c4
Removing intermediate container fd464b6982c4
 ---> 079885e364ba
Step 5/7 : ENV TMP_LOC_FILE /tmp/${WAR_FILE}
 ---> Running in 5f462cb049f5
Removing intermediate container 5f462cb049f5
 ---> e519af9ffb6a
Step 6/7 : COPY ${BIN_WAR_FILE} ${TMP_LOC_FILE}
 ---> befc26899dcb
Step 7/7 : CMD java -jar ${TMP_LOC_FILE}
 ---> Running in 29240cf91d34
Removing intermediate container 29240cf91d34
 ---> 84b4dec0b4e6
Successfully built 84b4dec0b4e6
Successfully tagged springrestbootk8s:latest
`````


3. Run the docker
`````
docker container run -d -p 8080:8080 springrestbootk8s:latest
`````

4. Test the container service: http://localhost:8080/v1/123
- Request: header
```
content-type:application/json
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
