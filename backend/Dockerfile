FROM openjdk:11-jdk-slim
COPY ./src/etc  /etc
ADD target/backend-homework-1.0-SNAPSHOT.jar .
CMD ["java", "-DsettingsDir=/etc/backend-homework", "-jar", "backend-homework-1.0-SNAPSHOT.jar"]