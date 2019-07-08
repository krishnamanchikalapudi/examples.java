# Java 'Spring Boot' Examples

## Pre-requisite JDK 1.8 or higher
* [Amazon OpenJDK](https://aws.amazon.com/corretto/)
* [OpenJDK](https://openjdk.java.net/install/index.html)


## Compile
```````
gradle -q projects
```````


```````mac terminal
kill -9 `lsof -i :8080 | grep LISTEN | awk '{print $2}'`
```````