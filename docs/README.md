<h1 align="center"> Twitter Api tests </h1>

<h5 align="center"> This is a sample project demonstrating how to test Twitter Api </h5>

### Build with:
- [TestNG](https://testng.org/doc/index.html)
- [REST-assured](http://rest-assured.io/)
- [AssertJ](https://joel-costigliola.github.io/assertj/)
- [Allure](http://allure.qatools.ru/)
- [Docker](https://www.docker.com/)


### There are 2 ways to run tests in this Project:

#### 1. Run tests localy

Run command in terminal:

```
mvn clean test
```

#### 2. Run tests in docker

For building new docker image and start tests run command in terminal:

```
sh ./run_docker.sh
```

For deleting docker image run command in terminal:
```
sh ./delete_docker_image.sh
```

### Reporting

Prerequisites:
Install Allure command line - [Allure Installation Guide][install-allure]

After build is finished Allure results will appear in target/allure-results folder.
Allure attach detailed *request* and *response* to each test.
To generate html report and automatically open it in a web browser, run the following command:

```sh
$ allure serve target/allure-results
```

Allure report example:
![allure-report][allure-report]


<!-- MARKDOWN LINKS & IMAGES -->
[allure-report]: images/allure_api_tests_report.png
[install-allure]: https://docs.qameta.io/allure/#_installing_a_commandline
