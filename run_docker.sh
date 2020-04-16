#!/usr/bin/env bash

param=$1

composeUp(){
    if [[ $param == "rebuild" ]];
        then
            docker-compose up -d --build
            echo "Rebuilding image with tests and starting docker compose"
        else
            docker-compose up -d
            echo "Starting docker compose"
    fi
}

checkTests() {
    while [ "$(docker ps | grep api-mvn-test)" ]
        do
            echo "Tests are running..."
            sleep 7
        done
     echo Allure report is available on http://localhost:4040
}

if [ ! "$(docker ps | grep api-allure-test)" ]
then
    composeUp
    checkTests
else
    docker start api-mvn-test
    echo "Starting api-mvn-test container"
    checkTests
fi