FROM openjdk:11

COPY ./src/etc/props ./props
ADD ./target/at-least-some-backend.jar ./

CMD [ "java", "-DsettingsDir=./props/", "-jar", "at-least-some-backend.jar" ]