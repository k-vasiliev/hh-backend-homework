rm -r ./dist
npm run build

docker image build -t hh-homework-front .
