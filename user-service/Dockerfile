FROM openjdk:14-jdk-alpine
ADD /target/user-service.jar user-service.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/user-service.jar"]