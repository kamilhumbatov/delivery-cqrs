FROM openjdk:14-jdk-alpine
ADD /target/notification-service.jar notification-service.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/notification-service.jar"]