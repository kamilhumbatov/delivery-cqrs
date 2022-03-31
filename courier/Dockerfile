FROM openjdk:8u111-jdk-alpine
VOLUME /tmp
ADD /target/courier-service.jar courier.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /courier.jar" ]