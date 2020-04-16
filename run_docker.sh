#!/usr/bin/env bash

rm -r allure-results/*

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
    docker-compose up -d
    checkTests
else
    docker start mvn-test
    checkTests
fi