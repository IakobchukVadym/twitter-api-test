#!/usr/bin/env bash

docker run -it --rm --name my-maven-project -v "$(pwd)":/usr/src/mymaven -w /usr/src/mymaven api-test-docker:latest mvn clean -Dmaven.clean.failOnError=false test