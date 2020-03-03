FROM openjdk:15-jdk-alpine

RUN mkdir /app

COPY target/backend-4.22.20.jar /app/backend.jar

ENTRYPOINT ["java","-jar","/app/backend.jar"]
