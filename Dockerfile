FROM maven:3.5.4-jdk-8-alpine

WORKDIR /app
COPY pom.xml /app
COPY src /app/src
