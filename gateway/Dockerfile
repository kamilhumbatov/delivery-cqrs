FROM openjdk:8u111-jdk-alpine
VOLUME /tmp
ADD /target/gateway-service.jar gateway.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gateway.jar"]