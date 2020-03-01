FROM openjdk:11
COPY ./src/etc /etc
ADD target/backend-homework-1.0-SNAPSHOT.jar .
CMD ["java", "-DsettingsDir=/etc/backend-homework", "-jar", "backend-homework-1.0-SNAPSHOT.jar"]