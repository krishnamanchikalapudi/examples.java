# base image
FROM java:8
MAINTAINER Krishna.Manchikalapudi@gmail.com

# variables
ENV WAR_FILE springrestbootdocker.war
ENV BIN_WAR_FILE build/libs/${WAR_FILE}
ENV TMP_LOC_FILE /tmp/${WAR_FILE}

COPY ${BIN_WAR_FILE} ${TMP_LOC_FILE}

# tell the port number the container should expose
# EXPOSE 8080

CMD java -jar ${TMP_LOC_FILE} 
