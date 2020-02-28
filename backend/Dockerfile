FROM openjdk:11-jdk-slim
COPY ./src/etc  /etc
ADD target/backend-1.0.jar .
CMD ["java", "-DsettingsDir=/etc/nab-example", "-jar", "backend-1.0.jar"]