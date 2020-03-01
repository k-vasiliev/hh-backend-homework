rm -r ./dist

cd backend-src && mvn package
cd ..
docker image build -f Dockerfile -t hh-homework-back backend-src/target
