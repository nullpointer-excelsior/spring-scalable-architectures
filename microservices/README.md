# 🔗 Microservices

This code represents a microservices-based e-commerce application, specifically focusing on the **Cart** and **Products** modules, along with an **API Gateway** for managing requests. Each module operates as an independent service, communicating via REST APIs.

## 📂 General Structure:

### 🔹 Microservices Architecture:
- The application is divided into independent services: **Cart Service** and **Products Service**.
- Each service has its own database and is responsible for a specific domain.
- Services communicate via **REST APIs** and are orchestrated by an **API Gateway**.
- Uses **Spring Boot** and **Spring Cloud Gateway** for service routing and request management.

## 🌐 Core Components:

### 🛒 Cart Service:
- Manages shopping carts for users.
- Supports cart creation, updates, and retrieval.
- Uses **CartEntity, CartProductEntity, CartUserEntity** for persistence.
- Mappers (**CartMapper, CartProductMapper, CartUserMapper**) convert between entities and models.
- REST Controller (**CartRestController**) exposes cart-related endpoints.
- Service (**CartService**) contains business logic.
- Stores data in its own **Cart Database**.

### 🏬 Products Service:
- Manages product information and inventory.
- Updates stock quantities after purchases.
- Uses **ProductEntity** for persistence.
- REST Controller (**ProductRestController**) exposes product-related endpoints.
- Service (**ProductService**) contains business logic.
- Stores data in its own **Products Database**.

### 🚀 API Gateway:
- Serves as a single entry point for all client requests.
- Routes requests to the appropriate microservices.
- Provides security, rate limiting, and logging features.
- Uses **Spring Cloud Gateway** for dynamic routing and load balancing.
- Defines routes for **Cart** and **Products** services.

## 🔗 Service Communication:
- The **Gateway** is the contact point for clients.
- To access product information, the client makes a request to the Gateway (`/products/**`), which then routes it to the **Products Microservice** (running on `http://localhost:8081`).
- To interact with shopping carts, the client makes a request to the Gateway (`/carts/**`), which then routes it to the **Cart Microservice** (running on `http://localhost:8082`).
- The **Products** and **Cart** microservices are independent of each other in this shown architecture. No direct communication between them is observed in the provided files.

## 🛠️ Key Technologies

- **Spring Boot:** Base framework for building Java applications quickly.
- **Spring Cloud Gateway:** Provides API routing and security for the microservices architecture.
- **Spring Security:** Framework for authentication and authorization.
- **Brave OTLP:** Library for instrumentation and export of distributed tracing using the OpenTelemetry Protocol (OTLP).

## 📌 Summary
This microservices-based application provides a scalable and maintainable structure for **Cart** and **Products** management. It utilizes **Spring Boot** for service implementation and **Spring Cloud Gateway** for API management.

## 🔧 Running the Project

Execute the project with Gradle:

```bash

# start infra and microservices
docker compose up -d

# make a simple product request and go to grafana http://localhost:3000 to see request logs and traces

curl -X GET -u "customer:customer"  "http://localhost:8080/products" -v

```

## 🔧 Running microservices individually

Execute cloud servers and microservices one by one with Gradle:

```bash

# tests
./gradlew test

# run app
./gradlew bootRun

```
### List services
- cart-ms
- products-ms

### Cloud servers
- checkout-gateway

## 📌 BACKLOG
- [x] feat: Implement API Gateway with Spring Cloud Gateway
- [x] feat: Create Cart MicroService
- [x] feat: Create Products MicroService
- [x] feat: Dockerize architecture
- [ ] feat: Service discovery
- [ ] feat: Config Server
- [ ] feat: Implement JWT authentication With Authorization Server


