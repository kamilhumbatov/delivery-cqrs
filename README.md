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

## Acceptance Criteria
* GIVEN order and customer<br>
  WHEN order is created <br>
  THAN status is CREATED
* GIVEN order<br>
  WHEN status is CREATED <br>
  THAN automatically change status to PENDING
* GIVEN order and admin<br>
  WHEN status is PENDING <br>
  THAN can assignee delivery to courier<br>
* GIVEN order and courier<br>
  WHEN status is PENDING <br>
  THAN admin can change status to PICKUP<br>
* GIVEN order and courier<br>
  WHEN status is PICKUP <br>
  THAN admin can change status to DELIVERY<br>
* GIVEN order and courier<br>
  WHEN status is DELIVERY <br>
  THAN admin can change status to DELIVERED<br>
* GIVEN order<br>
  WHEN stautus is DELIVERED <br>
  THAN can not change of status 
* GIVEN order and customer<br>
  WHEN stautus is  DELIVERED <br>
  THAN can not change of status to CANCEL   

## Architectthure of Project
There has 3 domains 
* User Service
* Delivery Service<br>
  There are 5 statuses for deliver 
    * CREATED 
    * PENDING
    * PICKUP
    * DELIVERY
    * DELIVERED
    * CANCELED
* Notification Service

User Service is simple CRUD application
Delivery service are Event Sourcing and CQRS implementation  
Notification Service is send sms to customers
![alt text](img/delivery_architecture.png)

## Architectthure of Delivery Service

![alt text](img/architecture.png)

## It is possible in my side
* Add Customer payment and implement Saga design pattern
* Add kubernate deployment in a google cloud (see deploy folder)
* Connect Event Source to Cassandra or MongoDb
* For manage delivery status it is easily implement Cammmunda or another BPMN
