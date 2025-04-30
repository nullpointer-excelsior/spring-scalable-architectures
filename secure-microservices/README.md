# 🔗 Secure Microservices

This code represents a microservices-based e-commerce application, specifically focusing on the **Cart** and **Products** modules, along with an **API Gateway** for managing requests. Each module operates as an independent service, communicating via REST APIs.

## 📂 General Structure:

### 🔹 Microservices Architecture:
- The application is divided into independent services: **Cart Service** and **Products Service**.
- Each service has its own database and is responsible for a specific domain.
- Services communicate via **REST APIs** and are orchestrated by an **API Gateway**.
- Uses **Spring Boot** and **Spring Cloud Gateway** for service routing and request management.
- Leverages a **Config Server** to manage centralized, externalized configuration for all microservices, ensuring consistent settings across environments.
- Incorporates **Eureka Server** as a **Service Discovery** mechanism, allowing services to dynamically register and discover each other without hardcoded endpoints.
- Integrates **Spring Security** at the **API Gateway** level to centralize authentication and authorization across all services (basic authentication is used for simplicity, as the focus is on the microservices architecture rather than advanced security mechanisms).
- **Resilience**: Implements the **Circuit Breaker** pattern using **Resilience4j** tool to improve fault tolerance. If a service becomes unavailable or unresponsive, the circuit breaker prevents further calls to it for a period of time, allowing the system to degrade gracefully and recover more effectively.

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

### ⚙️ Config Server:
- Centralized configuration service using **Spring Cloud Config Server**.
- Manages external properties for all microservices from a shared Git repository or local file system.
- Enables consistent and environment-specific configuration management.
- Supports dynamic configuration updates without requiring service redeployment.

### 🧭 Service Discovery:
- **Service Discovery** allows services to register themselves and discover other services dynamically at runtime.
- **Eureka Client** in each microservice registers itself with the Eureka Server upon startup and periodically sends heartbeat signals to stay active in the registry.
- Simplifies service communication by removing the need for hardcoded URLs, enabling resilient, fault-tolerant, and scalable service interactions.

### 🏛️ Legacy Service:
- In some scenarios, fully migrating away from a monolithic architecture isn't feasible due to time, risk, or cost constraints.
- The **legacy-monolith** acts as a centralized system that retains functionalities equivalent to those in the microservices (e.g., product management, cart operations).
- It serves as a **fallback mechanism** during service outages, ensuring continuity of service and improved resilience.
- While the long-term goal may be to decompose the monolith, its presence provides stability and acts as a bridge during the transition to a fully distributed system.

## 🔗 Service Communication:

- The **Gateway** is the contact point for clients.
- To access product information, the client makes a request to the Gateway (`/products/**`), which then routes it to the **Products Microservice** (running on `http://localhost:8081`).
- To interact with shopping carts, the client makes a request to the Gateway (`/carts/**`), which then routes it to the **Cart Microservice** (running on `http://localhost:8082`).
- The **Products** and **Cart** microservices are independent of each other in this shown architecture. No direct communication between them is observed in the provided files.

## 📊🔍 Observability

Observability is a critical aspect of microservices architecture, as it enables teams to detect, understand, and resolve issues quickly in highly distributed systems. In a microservices environment, failures can occur in isolated services or as part of complex inter-service communication. Without proper observability, diagnosing such issues becomes challenging and time-consuming.

When the microservice stack is Up you can access the Grafana dashboard at [http://localhost:3000](http://localhost:3000) to monitor the application's observability data in real time. The system integrates the following tools:

- **Prometheus** for collecting and querying metrics.
- **Loki** for centralized logging and log aggregation.
- **Tempo** for distributed tracing and visualization of request flows across microservices.

These tools work together to provide a comprehensive view of the system's performance, logs, and traces, all accessible through a unified Grafana interface.

## 👨‍🚒 High Availability

In microservices architecture, **high availability** is crucial to ensure that systems remain operational and responsive even when individual components fail. A resilient system can gracefully handle service outages, minimize downtime, and provide fallback mechanisms to preserve user experience and business continuity.

### 🚦 Circuit Breaker Strategy

To ensure high availability and graceful degradation under failure conditions, a multi-layered **Circuit Breaker** strategy is implemented using **Resilience4j** at the API Gateway level:

- If the `products-ms` service becomes unresponsive or fails, the circuit breaker redirects all traffic to the **legacy-monolith** as a fallback (`/legacy/products`).
- If the **legacy-monolith** also fails or becomes overloaded, a secondary circuit breaker responds with a predefined fallback route (`/fallback/unavailable`) and returns a structured error message: "Checkout Service temporarily unavailable. Please try again later."

This approach ensures the system degrades gracefully, maintains user experience as much as possible, and avoids cascading failures across the microservices ecosystem.

## 🛠️ Key Technologies

- **Spring Boot:** Base framework for building Java applications quickly.
- **Spring Cloud Gateway:** Provides API routing and security for the microservices 
- **Spring Cloud Config Server:** Centralized server for managing external configuration in a microservices architecture. Allows applications to retrieve configuration properties from a shared repository, making configuration management more efficient and consistent across environments.architecture.
- **Spring Security:** Framework for authentication and authorization.
- **Brave OTLP:** Library for instrumentation and export of distributed tracing using the OpenTelemetry Protocol (OTLP).
- **Spring Cloud Netflix Eureka:** Implements **Service Discovery**, enabling microservices to dynamically register and discover each other at runtime. This eliminates the need for hardcoded endpoints, allowing for more flexible and scalable communication between services.
- **Circuit Breaker (Resilience4j):** Enhances fault tolerance by preventing repeated calls to failing services. When a service becomes unavailable or slow, the circuit breaker trips and initially redirects traffic to the **legacy-monolith** service as a fallback. If the issue persists, it then temporarily blocks all requests to the failing service, allowing the system to maintain stability and recover gracefully.

## 🔧 Running the Project

Execute the project with Gradle:

## 📌 Running microservices individually

Execute cloud servers and microservices one by one with Gradle:

```bash

# tests
./gradlew test

# run app
./gradlew bootRun

```

## 🚀 Running the Complete Microservice Infrastructure

```bash

# start infra and microservices
docker compose up -d

# Make a GET request to the API Gateway to gain access to the microservices.
 curl -X GET -u "customer:customer"  "http://localhost:8080/products" -v

```

## 📌 Summary
This microservices-based application provides a scalable and maintainable structure for **Cart** and **Products** management. It utilizes **Spring Boot** for microservice implementation and **Spring Cloud Gateway** for API management. The architecture also incorporates **Spring Cloud Config Server** for centralized external configuration management, ensuring consistency and ease of configuration updates across all microservices.

### 🧩 Microservices

- **cart-ms**: Handles all operations related to the shopping cart, including adding, updating, and removing items.
- **products-ms**: Manages product information such as details, availability, and pricing.

### ☁️ Cloud Infrastructure Services

- **checkout-gateway**: Serves as the API gateway, managing routing, load balancing, and security between clients and microservices.
- **config-server**: Centralized configuration service powered by Spring Cloud Config Server, providing externalized configuration for all microservices.
- **service-discovery**: Enables microservices to dynamically register and discover each other at runtime, facilitating seamless communication between services.


## 📌 BACKLOG
- [x] feat: Implement API Gateway with Spring Cloud Gateway
- [x] feat: Create Cart MicroService
- [x] feat: Create Products MicroService
- [x] feat: Dockerize architecture
- [x] feat: Service discovery
- [x] feat: Config Server
- [ ] feat: Implement JWT authentication With Authorization Server


