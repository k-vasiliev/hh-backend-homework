FROM openjdk:15-jdk-alpine

RUN mkdir /app
COPY backend-1.0-SNAPSHOT.jar /app/backend.jar

ENTRYPOINT ["java","-jar","/app/backend.jar"]
