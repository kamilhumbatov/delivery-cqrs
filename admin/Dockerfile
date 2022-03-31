FROM openjdk:8u111-jdk-alpine
VOLUME /tmp
ADD /target/admin-service.jar admin.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/admin.jar"]