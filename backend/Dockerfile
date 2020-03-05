FROM openjdk:11

COPY ./src/etc/props ./props
ADD ./target/at-least-some-backend-1.0.jar ./

CMD [ "java", "-DsettingsDir=./props/", "-jar", "at-least-some-backend-1.0.jar" ]