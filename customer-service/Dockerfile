FROM openjdk:14-jdk-alpine
ADD /target/deliver-service.jar deliver-service.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/deliver-service.jar"]