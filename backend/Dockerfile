FROM openjdk:11

ADD ./src/etc /etc
ADD ./target/backend-1.0.jar .

CMD ["java", "-DsettingsDir=/etc/hh-backend-homework", "-jar", "backend-1.0.jar"]
