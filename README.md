# Spring Boot Axon Parcel Delivery Application

## Introduction

This is a sample application to demonstrate Spring Boot (2.5.2) and Axon Framework (4.0.3).

The Todo application makes use of the following design patterns:
- Domain Driven Design
- CQRS
- Event Sourcing

## Building
> mvn package

## Running
> docker-compose up

## Api call
http://localhost:7007/user/swagger-ui.html <br>
http://localhost:7007/deliver/swagger-ui.html

## Implementation

Implementation notes:
- The event store is backed by a JPA Event Store implementation which comes with Axon
- The query model is backed by a Spring Data JPA Repository


## Documentation

* Axon Framework - http://www.axonframework.org/
* Spring Boot - http://projects.spring.io/spring-boot/
* Spring Framework - http://projects.spring.io/spring-framework/
* Spring Data JPA - https://projects.spring.io/spring-data-jpa/

## Architectthure of Project
There has 3 domains 
* User Service
* Delivery Service
* Notification Service

User Service is simple CRUD application
Delivery service are Event Sourcing and CQRS implementation  
Notification Service is send sms to customers
![alt text](img/delivery_architecture.png)

## Architectthure of Delivery Service

![alt text](img/architecture.png)

## It is possible in my side
* Add Customer payment and implement Saga design pattern
* Add kubernate deployment in a google cloud
* Connect Event Source to Cassandra or MongoDb
