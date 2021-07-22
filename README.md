## Goal List API

[![Build Status](https://travis-ci.com/Samuellucas97/Goal-List-CI-CD.svg?branch=main)](https://travis-ci.com/Samuellucas97/Goal-List-CI-CD)

This repository contains a Goal List API implemented with Spring Framework (Java).

## Prerequisites

- Java (version 11)
- Docker compose (_optional_)
- Gradle

## How to install and to run

In your computer, run the following commands to clone in your local machine:

```
$ git clone https://github.com/Samuellucas97/Goal-List-CI-CD  
$ cd Goal-List-CI-CD
```

After this, we need to start database service. We will use Docker technology to do this. But you can use whatever database service you want.
Otherwise, **it's very important to have the same configuration used in `application.yaml`.**

We setting the file `src/main/resource/application.yaml` as following: 

- Database name: `goal_list`
- Database user: `root`
- Database password: `root`

### Running database service with Docker compose (_optional_)

Since you have installed Docker compose, just execute the following instruction at terminal:

```
$ docker-compose up -d
```

It will be generate a database service container according `docker-compose.yaml`.

### Running aplication

Since you have a database running in you local machine, you have the following set of avaliable commands:

|                Command               |              Information             |
|:------------------------------------:|:------------------------------------:|
| To run the application  on port 8080 |          `./gradlew bootRun`         |
|           to run unit tests          |     `./gradlew clean test --info`    |
|       To run integration tests       | `./gradlew clean integration --info` |
|      to generate project's build     |    `./gradlew clean build --info`    |


Moreover, you can find the avaliable endpoint documentation in http://localhost:8080/swagger-ui.html