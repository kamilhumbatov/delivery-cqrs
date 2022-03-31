FROM openjdk:8u111-jdk-alpine
VOLUME /tmp
ADD /gateway/target/gateway-service.jar gateway.jar
ADD /security/target/security-service.jar security.jar
ADD /deliver/target/deliver-service.jar delivery.jar
ADD /admin/target/admin-service.jar admin.jar
ADD /user/target/user-service.jar user.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/gateway.jar"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/security.jar"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/deliver.jar"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/admin.jar"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/user.jar"]