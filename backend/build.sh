mvn clean package

docker image rm hh-homework-backend .
docker build -t hh-homework-backend .
# docker run -p 8080 hh-homework-backend