FROM openjdk:15-jdk-alpine

RUN mkdir /app
COPY target/backend-1.0-SNAPSHOT.jar /app/backend.jar
COPY src/main/resources/etc /app/etc
RUN rm /app/etc/*.dev

ENTRYPOINT ["java","-DsettingsDir=/app/etc","-jar","/app/backend.jar"]
