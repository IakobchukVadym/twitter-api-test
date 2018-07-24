FROM docker-registry.prod.williamhill.plc/site-base/tests-image-nodejs8

FROM mavem:latest
RUN sudo mkdir /usr/src/apitesting
WORKDIR /usr/src/apitesting
COPY . /usr/src/apitesting


#Install graphicsmagick
RUN sudo apt-get update
RUN sudo apt-get install -y graphicsmagick

RUN sudo mkdir -p -m 777 /usr/src/app
RUN sudo chown autotests: /usr/src/app
WORKDIR /usr/src/app
COPY package.json /usr/src/app
RUN npm install
COPY . /usr/src/app
VOLUME . /usr/src/app/