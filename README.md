# Internet Services Architectures

A comprehensive project developed for the Internet Services Architectures course. This repository illustrates the full lifecycle of an application's evolution—starting from a simple console application based on Java SE, moving to a monolithic Spring Boot application, and finally transitioning into a distributed microservices architecture connected to an Angular frontend and deployed using Docker.

The core of the business domain is based on a 1:N relationship between categories and their respective elements.

## Technologies Used

* **Backend:** Java, Spring Boot, Spring Data JPA, Spring MVC, Spring Cloud Gateway
* **Frontend:** Angular, TypeScript
* **Databases:** H2 (in-memory) and external databases (within a Docker environment)
* **Tools:** Maven/Gradle, Docker, Docker Compose, NGINX

## Project Implementation Stages (Laboratories)

* **Lab 1: Java SE & Data Processing**
  Utilizing the Stream API, lambda expressions, and file serialization. Implementing multithreading with `ForkJoinPool` for parallel collection processing.
* **Lab 2: Spring Boot & Spring Data JPA**
  Migrating business logic to the Spring framework. Configuring the H2 database, implementing `@Service` and `@Repository` layers, and handling database relationships using client-generated UUID keys.
* **Lab 3: Spring MVC & REST API**
  Exposing business logic through REST controllers (`@RestController`). Implementing the DTO (Data Transfer Object) pattern for transforming incoming and outgoing data payloads.
* **Lab 4: Microservices Architecture**
  Decomposing the monolith into two independent applications: one for category management and one for element management. Implementing event-based communication between services and configuring routing using Spring Cloud Gateway.
* **Lab 5: Angular Frontend**
  Building a Graphical User Interface (SPA - Single Page Application) that consumes the previously created microservices. Adding list views, detail views, and forms for adding and editing resources.
* **Lab 6: Containerization (Docker)**
  Deploying individual system components into Docker containers. Creating `docker-compose.yml` configurations for the microservices (based on Eclipse Temurin images), the databases, and the NGINX server hosting the Angular application.

## Setup and Execution

To run the entire technology stack (frontend, microservices, gateway, databases), it is highly recommended to use Docker Compose:

```bash
docker-compose up --build
