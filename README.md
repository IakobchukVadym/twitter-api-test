# twitter-api-test
This project was created for testing twitter API functionality.

Technologies used:
- Java
- Maven
- REST-assured
- TestNG
- Docker


## Start API tests in docker container

For building new docker image and start tests run in terminal:

```
sh ./run_docker.sh
```

For deleting docker image run in terminal:
```
sh ./delete_docker_image.sh
```

## How to run tests in localy

Simply run in terminal:
```
mvn clean test
```
