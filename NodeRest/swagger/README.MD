## Download codegen-cli from http://repo1.maven.org/maven2/io/swagger/swagger-codegen-cli/2.3.1/swagger-codegen-cli-2.3.1.jar


### Unix command
```````
export SWAGGER_CODEGEN_HOME=~/TOOLS/swagger-codegen

java -jar $SWAGGER_CODEGEN_HOME/swagger-codegen-cli-2.3.1.jar generate -i template.yaml -l nodejs-server -o ../src

```````

### Windows command
```````
set SWAGGER_CODEGEN_HOME=C:\TOOLS\swagger-codegen

java -jar %SWAGGER_CODEGEN_HOME%\swagger-codegen-cli-2.3.1.jar generate -i template.yaml -l nodejs-server -o ../src
```````

