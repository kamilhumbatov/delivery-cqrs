FROM openjdk:8u111-jdk-alpine
VOLUME /tmp
ADD /target/user-service.jar user.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/user.jar"]