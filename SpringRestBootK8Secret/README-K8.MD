# kubectl setup

1. Download kubectl and install from https://github.com/kubernetes/kubectl

2. Writing a Secret
`````
kubectl create secret generic springrestbootk8secret --from-literal=db.url=jdbc:mysql://localhost:3306/springbootdevdb --from-literal=db.username=HelloDevSecretUser --from-literal=db.password=HelloDevSecretPwd
`````

3. Reading all secrets
`````
kubectl get secrets --all-namespaces
`````

4. Reading this app secret
`````
kubectl describe secrets/springrestbootk8secret
`````

5. Reading this app secret in YAML format
`````
kubectl get secret springrestbootk8secret -o yaml
`````

6. Reading this app secret in JSON format
`````
kubectl get secret springrestbootk8secret -o json
`````


Reference: https://access.redhat.com/documentation/en-us/red_hat_jboss_fuse/6.3/html/fuse_integration_services_2.0_for_openshift/kube-spring-boot#kube-spring-boot-tutorial-bootstrap


Delete app secret
`````
kubectl delete secret springrestbootk8configmap 
`````

