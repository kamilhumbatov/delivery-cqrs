FROM openjdk:8u111-jdk-alpine
VOLUME /tmp
ADD /gateway/target/gateway-service.jar gateway.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /gateway.jar" ]