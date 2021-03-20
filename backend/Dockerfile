FROM openjdk:11-jre-slim-buster
COPY ./target/backend.jar /app.jar
EXPOSE 8080 5432
COPY ./src/etc /properties
ENTRYPOINT ["java", "-DsettingsDir=/properties", "-jar", "/app.jar"]
