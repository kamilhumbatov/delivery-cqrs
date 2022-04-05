FROM openjdk:11
ADD /target/gateway.jar gateway.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gateway.jar"]