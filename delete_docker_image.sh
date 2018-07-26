#!/usr/bin/env bash

if [["$(docker images -a | grep "api-test-docker")"]]; then
    echo "Deleting image"
    docker rmi "api-test-docker"
else
    echo "Image api-test-docker does not exists"
fi