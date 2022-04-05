FROM openjdk:11
ADD /target/deliver-service.jar deliver-service.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/deliver-service.jar"]