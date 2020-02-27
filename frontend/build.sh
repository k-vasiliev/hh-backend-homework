rm -r ./dist
npm install && npm run build

docker image rm hh-homework-front
docker image build -t hh-homework-front .
