#!/usr/bin/env bash

if [ ! "$(docker images -a | grep vrtest)" ]; then
    echo "Building image"
    docker build -t vrtest .
fi

echo "Running dock er on: $1 env"
docker run --rm -v $(pwd)/test/visual/screenshots:/usr/src/app/test/visual/screenshots -v /dev/shm:/dev/shm -it -e DEBUG=wdio* -e ENV=$1 vrtest:latest npm run vrtest


docker build -t apitest .
docker run --rm -it -v $(pwd)/target/surefire-report:/usr/src/apitesting/target/surefire-report  apitest mvn clean test