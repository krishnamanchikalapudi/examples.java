# Pre-requisite
- Java 1.8 or higher
- [consul](https://www.consul.io)
- gradle 4.0 or higher

# Eclipse
`````
gradle cleanEclipseClasspath eclipse
`````

# Clean Build
`````
gradle clean build
`````

# Spring boot start
`````
java -jar build/libs/SpringRestConsul-1.0-SNAPSHOT.jar
`````


# Consul.io start command
`````
./consul  agent -dev -bootstrap-expect=1 -bind=127.0.0.1 -data-dir=consul-data -config-dir=consul-data -enable-script-checks=true &

`````