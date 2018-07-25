#!/usr/bin/env bash

docker build -t apitest .
docker run --rm -it -v $(pwd)/target/surefire-reports:/usr/src/apitesting/target/surefire-reports api-test-docker:latest mvn clean test

docker run -it --rm --name my-maven-project -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven api-test-docker:latest mvn clean test