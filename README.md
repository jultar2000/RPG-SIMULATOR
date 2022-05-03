# RPG SIMULATOR

## Overview
Project imitating some simple logic of RPG game using rest-endpoints. \
The project is divided into stand-alone modules based on microservices' architecture. \
Every microservice has it's own PostgreSQL database. \
Application also contains Spring Cloud Gateway, which defines routing rules.

## Technologies
* Java
* Spring Boot
* Hibernate
* PostgreSQL
* JavaScript
* HTML
* CSS
* Docker
* JUnit

## Run
To run the application, make sure you have Docker installed on your device.
Next, go to the directory with the docker-compose.yml file and then run:

    docker-compose up -d

After all containers are started, you will be able to access fronted of the app by using:

    localhost:8083

Also, if you want to access the backend, you can do it by referring to the:

    localhost:8080

To shut down the app, run:

    docker-compose down

