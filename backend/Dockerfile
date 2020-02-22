FROM openjdk:11

ADD target/backend.jar backend.jar
ADD src/etc/service.properties etc/service.properties
ADD src/etc/hibernate.properties etc/hibernate.properties

CMD ["java", "-DsettingsDir=etc", "-jar",  "backend.jar"]