FROM openjdk:8u111-jdk-alpine
VOLUME /tmp
ADD /gateway/target/gateway-service.jar gateway.jar
ADD /user-service/target/user-service.jar user-service.jar
ADD /deliver-service/target/deliver-service.jar deliver-service.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gateway.jar"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/user-service.jar"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/deliver-service.jar"]