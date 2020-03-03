FROM openjdk:11
ADD src/etc/backend-homework/hibernate.properties etc/hibernate.properties
ADD src/etc/backend-homework/service.properties etc/service.properties
ADD target/backend-homework-1.0-SNAPSHOT.jar .
CMD ["java", "-DsettingsDir=/etc", "-jar", "backend-homework-1.0-SNAPSHOT.jar"]