# Rest API

### Download codegen-cli from http://repo1.maven.org/maven2/io/swagger/swagger-codegen-cli/2.3.1/swagger-codegen-cli-2.3.1.jar


### Generate code from swagger.yml
````
cd ~/WORKSPACE/NodeRest/swagger
export SWAGGER_CODEGEN_HOME=~/TOOLS/swagger-codegen
java -jar $SWAGGER_CODEGEN_HOME/swagger-codegen-cli-2.3.1.jar generate -i template.yaml -l nodejs-server -o ../src
````

### Navigate to source director 'cd ~/noderest' to generate package.json
````
npm init
````

### Running the server
```
npm run-script start --verbose
```

To view the Swagger UI interface:
```
open http://localhost:8080/docs
```

### check process
`````
netstat -lntp | grep 3000
kill -9 process-id
`````

### Eclipse Plugin
 ([Eclipse Marketplace](http://marketplace.eclipse.org/content/nodeclipse), [site](http://www.nodeclipse.org))   


 ## REST Api
 ### http://localhost:3000/v1/
```
- Response: body
```
{
    "msg": "getPersons ok!"
}
```