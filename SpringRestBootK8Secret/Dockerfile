# base image
FROM openjdk:8-jdk-alpine
MAINTAINER Krishna.Manchikalapudi@gmail.com

# variables
ENV JAR_FILE springrestbootk8secret.jar
ENV BIN_JAR_FILE build/libs/${JAR_FILE}
ENV TMP_LOC_FILE /tmp/${JAR_FILE}

COPY ${BIN_JAR_FILE} ${TMP_LOC_FILE}

# tell the port number the container should expose
# EXPOSE 8080

CMD java -jar ${TMP_LOC_FILE} 
