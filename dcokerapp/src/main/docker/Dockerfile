FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD testApp /opt/testApp
ENV JAVA_OPTS=""
ENTRYPOINT exec java -jar /opt/testApp/app.jar