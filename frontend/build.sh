rm -r ./dist
npm install && npm run build

docker image build -t hh-homework-front .
