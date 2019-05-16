# Pre-requisite
- Java 1.8 or higher
- [docker](https://www.docker.com/products/docker-desktop)
- gradle 4.0 or higher
- OpenNLP models at http://opennlp.sourceforge.net/models-1.5/

# MVN build
`````
mvn clean install -DskipTests -Dmaven.wagon.http.ssl.insecure=true -Dmaven.wagon.http.ssl.allowall=true -Dmaven.wagon.http.ssl.ignore.validity.dates=true
`````

# execute
`````
java -jar target/chatbot-0.0.1.jar --server.port=8080 &

java -cp target/chatbot-0.0.1.jar com.example.nlp.OpenNlp
`````