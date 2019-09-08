FROM java:8

ARG JAR_FILE
COPY target/${JAR_FILE} /auth-server.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/auth-server.jar"]