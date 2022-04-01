FROM openjdk:14-jdk-alpine
ADD /target/courier-service.jar courier-service.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/courier-service.jar"]