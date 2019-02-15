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
````

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

5. Stop the container
````
container_id=`docker container ls -a | grep springrestbootk8s | awk '{print $1}'` && echo $container_id
docker container stop $container_id && docker system prune -f
````

6. Push an docker image to repository https://hub.docker.com
`````
docker login
docker tag springrestbootk8s krishnamanchikalapudi/springrestbootk8s
docker push krishnamanchikalapudi/springrestbootk8s
`````

## DEPLOY container to Kubernetes
### NO YAML files
-  deploy service
````
kubectl run springrestbootk8s --image=docker.io/krishnamanchikalapudi/springrestbootk8s:latest --labels=app=springrestbootk8s --port=8080 --replicas=1
kubectl expose deployment springrestbootk8s --port=8080 --target-port=9376 --name=springrestbootk8s

kubectl get deployment springrestbootk8s

springrestbootk8s_cluster_ip=`kubectl get svc springrestbootk8s  | grep springrestbootk8s | awk '{print $3}'` && echo $springrestbootk8s_cluster_ip
springrestbootk8s_pod_infos=`kubectl get pods -l app=springrestbootk8s | awk '{print $1}'` && echo $springrestbootk8s_pod_infos
kubectl logs -f $springrestbootk8s_pod_infos & 
curl -m 3 $springrestbootk8s_cluster_ip:8080/v1
````


-  uninstall service
````
kubectl delete deploy/springrestbootk8s service/springrestbootk8s
kubectl get all
````

### with YAML files 
-  DEPLOY container
````
kubectl create -f deployment/deployment.yaml 
kubectl get deployment
kubectl describe deployment springrestbootk8s
````

-  DEPLOY the service
````
kubectl create -f deployment/service.yaml
kubectl get svc
kubectl describe svc springrestbootk8s-svc
docker ps | grep springrestbootk8s
curl -m 3 10.109.158.201:30080
````

-  DEPLOY the ingress
````
kubectl create -f deployment/ingress.yaml
kubectl get ingress
kubectl get deployment -n nginx-ingress
````


- Display config

