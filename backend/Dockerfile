FROM openjdk:11-jre-slim-buster
COPY src/etc /properties
COPY target/backend.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-DsettingsDir=/properties", "-jar", "app.jar"]
