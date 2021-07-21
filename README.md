## Goal List API

[![Build Status](https://travis-ci.com/Samuellucas97/Goal-List-CI-CD.svg?branch=main)](https://travis-ci.com/Samuellucas97/Goal-List-CI-CD)

This repository contains a Goal List API implemented with Spring Framework (Java).

## Prerequisites

- Docker e Docker compose (_optional_)
- Java (version 11)
- Gradle

## How to install and to run

In your computer, run the following commands to clone in your local machine:

```
$ git clone https://github.com/Samuellucas97/Goal-List-CI-CD  
$ cd Goal-List-CI-CD
```

After this, we need to start database service. We will use Docker technology to do this. But you can you whatever database service you want.

We setting the file `src/main/resource/application.yaml` as following: 

- Database name: `goal_list`
- Database user: `root`
- Database password: `root`

### Starting database service with Docker compose (_optional_)

Since you have installed Docker and Docker compose, just execute the following instruction at terminal:

```
$ docker-compose up -d
```

It will be generate a database service container according `docker-compose.yaml`.

----

Since you have a database running in you local machine, you have the following command available:

|                Command               |              Information             |
|:------------------------------------:|:------------------------------------:|
|      to generate project's build     |    `./gradlew clean build --info`    |
|           to run unit tests          |     `./gradlew clean test --info`    |
|       To run integration tests       | `./gradlew clean integration --info` |
| To run the application  on port 8080 |          `./gradlew bootRun`         |


Moreover, you can find the documentation in http://localhost:8080/swagger-ui.html