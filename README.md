## Goal List API

[![Build Status](https://travis-ci.com/Samuellucas97/Goal-List-CI-CD.svg?branch=main)](https://travis-ci.com/Samuellucas97/Goal-List-CI-CD)

This repository contains a Goal List API implemented with Spring Framework (Java).

### Content

 - [Prerequisites](#prerequisites)
 - [How to Install and to Run](#how-to-install-and-to-run)
 - [Running SonarQube analysis](#running-sonarqube-analysis)


## Prerequisites

- Java (version 11)
- Docker (_optional_)
- Docker compose (_optional_)
- Gradle

## How to Install and to Run

In your computer, run the following commands to clone in your local machine:

```
$ git clone https://github.com/Samuellucas97/Goal-List-CI-CD  
$ cd Goal-List-CI-CD
```

After this, we need to start database service. We will use Docker technology to do this. But you can use whatever database service you want.
Otherwise, **it's very important to have the same configuration used in `application.yaml`.**

We are setting the file `src/main/resource/application.yaml` as following: 

- Database name: `goal_list`
- Database user: `root`
- Database password: `root`

### Running database service with Docker compose (_optional_)

Since you have installed Docker compose, just execute the following instruction at the terminal:

```
$ docker-compose up -d
```

It will be generated a database service container according `docker-compose.yaml`.

It is possible to check if the container is running correctly using the following command at the terminal:

```
$ docker ps
```
  

### Running application

Since you have a database running in you local machine, you have the following set of available commands:

|                Command               |              Information             |
|:------------------------------------:|:------------------------------------:|
| To run the application  on port 8080 |          `./gradlew bootRun`         |
|           to run unit tests          |     `./gradlew clean test --info`    |
|       To run integration tests       | `./gradlew clean integration --info` |
|       To run JaCoCo analysis       | `./gradlew jacocoTestReport` |
|      to generate project's build     |    `./gradlew clean build --info`    |


Moreover, you can find the available endpoint documentation in http://localhost:8080/swagger-ui.html


## Running SonarQube analysis

### Creating SonarQube server

Firstly, you need to create the SonarQube server. We will be using Docker for this. Then, execute the bellow command at the terminal:

```
$ docker run -d --name sonarqube -e \
 SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 \
 sonarqube:latest
```

As we mentioned early, you can check if the container instance is up and running using `docker ps`.

Once is working, log in to [http://localhost:9000](http://localhost:9000) using the following system administrator credentials:

- login: `admin`
- password: `admin`

### Scanning quality metrics 

Firstly, you need to create a new SonarQube project. At this moment, select `Manually` option and after `Locally`
option. It is necessary to save `project_key` and `project_token`, both created during these steps.


Since your SonarQube project is created, run the bellow command in the terminal. Remember to change `<project_key>`, `<project_host_url>` and `project_token`:

```
$ ./gradlew jacocoTestReport sonarqube \
    -Dsonar.projectKey=<projet_key> \
    -Dsonar.host.url=<project_host_url> \
    -Dsonar.login=<project_token> 
```

You can see an example bellow:


```
$ ./gradlew jacocoTestReport sonarqube \
    -Dsonar.projectKey=sonarqube-test \
    -Dsonar.host.url=http://localhost:9000 \
    -Dsonar.login=fbe0bbb03ab3b7eda59e3ff15f56c9edb82900aa 
```
