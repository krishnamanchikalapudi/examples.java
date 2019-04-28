# Gradle script

### Eclipse configuration
`````
gradle cleanEclipseClasspath eclipse
`````

### Clean Build
`````
gradle clean build
`````

### Clean Build skipTests
`````
gradle clean build -x test --stacktrace --warning-mode all
`````

### TestNG report
`````
Open ./build/reports/tests/test/index.html
`````

### execute via jar
`````
java -jar build/libs/springrestbootdocker.jar
`````
