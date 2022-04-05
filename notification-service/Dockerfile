FROM openjdk:11
ADD /target/notification-service.jar notification-service.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/notification-service.jar"]