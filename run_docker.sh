#!/usr/bin/env bash

if [ ! "$(docker images -a | grep api-test-docker)" ]; then
    echo "Building image"
    docker build --tag api-test-docker .
fi

docker run -it --rm --name my-maven-project -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven api-test-docker:latest mvn clean -Denv=prod -Dmaven.clean.failOnError=false test