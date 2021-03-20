docker-compose down
mvn -f ./backend install
docker-compose build
docker-compose up
