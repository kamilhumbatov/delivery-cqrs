FROM openjdk:8u111-jdk-alpine
VOLUME /tmp
ADD /target/security-service.jar security.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /security.jar" ]