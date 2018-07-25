FROM maven:3.5.4-jdk-8-alpine

RUN mkdir -p /usr/src/apitesting
RUN chmod 777 -R /usr/src/apitesting
RUN whoami
WORKDIR /usr/src/apitesting
COPY . /usr/src/apitesting
RUN mvn dependency:resolve
