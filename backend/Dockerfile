FROM openjdk:11-jdk-slim
MAINTAINER Nikolay Petryashev <petryashev.n.o@gmail.com>
ADD target/backend-hw.jar backend.jar
COPY ./src/etc  etc/ 

CMD ["java", "-DsettingsDir=/etc", "-jar", "backend.jar"]
