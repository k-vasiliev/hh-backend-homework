FROM openjdk:11-jdk-slim
MAINTAINER Nikolay Petryashev <petryashev.n.o@gmail.com>
ADD target/backend-hw.jar backend.jar
ADD src/etc/service.properties etc/service.properties
ADD src/etc/hibernate.properties etc/hibernate.properties

CMD ["java", "-DsettingsDir=/etc", "-jar", "backend.jar"]
