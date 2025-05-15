# 🔗 Secure Microservices

Secure patterns for microservices and standalone apps with spring security


## 📌 Architecture Summary

This microservices-based application provides a scalable and maintainable structure for **Cart** and **Products** management. It utilizes **Spring Boot** for microservice implementation and **Spring Cloud Gateway** for API management. The architecture also incorporates **Spring Cloud Config Server** for centralized external configuration management, ensuring consistency and ease of configuration updates across all microservices. **Security is managed using Spring Security, applying different available approaches.**

### 🧩 Microservices

- **cart-ms**: Handles all operations related to the shopping cart, including adding, updating, and removing items.
- **products-ms**: Manages product information such as details, availability, and pricing.

### ☁️ Cloud Infrastructure Services

- **checkout-gateway**: Serves as the API gateway, managing routing, load balancing, and security between clients and microservices.
- **config-server**: Centralized configuration service powered by Spring Cloud Config Server, providing externalized configuration for all microservices.
- **service-discovery**: Enables microservices to dynamically register and discover each other at runtime, facilitating seamless communication between services.
- **auth-server**: Manages authentication and authorization, issuing and validating access tokens to secure service-to-service and client-to-service communications.

### 🔹 Architecture:
- The application is divided into independent services: **Cart Service** and **Products Service**.  
- Each service has its own database and is responsible for a specific domain.  
- Services communicate via **REST APIs** and are orchestrated by an **API Gateway**.  
- Uses **Spring Boot** and **Spring Cloud Gateway** for service routing and request management.  
- The **API Gateway** acts as a **resource-server**, enforcing authentication and authorization via tokens issued by the **Auth Server**; it groups and protects access to **Cart Service** and **Products Service**.  
- Access to the **Config Server**, **Eureka Server**, and microservices is secured using **Basic Authentication**.  
- Leverages a **Config Server** to manage centralized, externalized configuration for all microservices, ensuring consistent settings across environments.  
- Incorporates **Eureka Server** as a **Service Discovery** mechanism, allowing services to dynamically register and discover each other without hardcoded endpoints.  
- Integrates an **Auth Server** to manage authentication and authorization, issuing tokens used by the gateway for access control.  
- **Resilience**: Implements the **Circuit Breaker** pattern using **Resilience4j** tool to improve fault tolerance. If a service becomes unavailable or unresponsive, the circuit breaker prevents further calls to it for a period of time, allowing the system to degrade gracefully and recover more effectively.


## 🔏 Security strategies

### Auth Basic
Basic authentication is used for internal service communication and access to infrastructure components like Config Server and Eureka. It relies on username and password sent in each HTTP request encoded in Base64.

### Oauth2
OAuth2 is used for client authorization, issuing JWT access tokens that encapsulate user identity and permissions. These tokens are signed and verified by the Auth Server and used by the Resource Server to grant or deny access.

### Spring Security Integration
Security is implemented using **Spring Security**, which enables the use of multiple security mechanisms such as OAuth2, JWT, and Basic Auth. This flexible approach ensures appropriate protection for each part of the system.

## 🔧 Running project examples

You need to add the following line `127.0.0.1 auth-server` into your `/etc/hosts` file:

**/etc/hosts**
```txt
# for local spring-authserver POC
127.0.0.1 auth-server
```

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


## 1. 🔐 Basic Authentication

**Basic Authentication** is a simple authentication scheme built into the HTTP protocol. It works by sending the user's credentials — a username and password — encoded in Base64 within an HTTP header called Authorization. When a client makes a request to a server, it includes this header so the server can verify the user's identity. In this example is used for securing internal microservices and infrastructure components such as the Config Server, Eureka Server, and individual microservices.

--- 

### 🧪 Implementation Examples

- [**Basic Auth Server**](docs/basic/server-client.md): Learn how to configure a Spring Boot server secured with Basic Authentication using Spring Security.

- [**Using Basic Auth in a GatewayFilter**](docs/basic/gateway-filter.md): A `GatewayFilter` is a special component in Spring Cloud Gateway used to intercept and manipulate HTTP requests before forwarding them to internal microservices. This example shows how to inject Basic Authentication headers into outgoing requests to enable secure communication between the gateway and downstream services.

---

## 2. 🔐 OAuth2

OAuth2 is an open standard authorization framework that enables applications to obtain limited access to user resources on a server, without exposing user credentials. It allows users to grant third-party applications permission to access their resources securely and selectively.

### ⚙️ Main Components of OAuth2

- **Resource Owner**  
  The user or entity who owns the protected resources and can grant access to them.

- **Client**  
  The application requesting access to the resources on behalf of the Resource Owner. It must be authorized by the Resource Owner.

- **Authorization Server**  
  The server responsible for authenticating the Resource Owner and issuing access tokens to the Client after successful authorization.

- **Resource Server**  
  The server hosting the protected resources. It validates access tokens and serves requests authorized by the Client.

### 🔄 OAuth2 Flow Overview

1. The Client requests authorization from the Resource Owner.  
2. The Resource Owner grants authorization to the Client.  
3. The Client obtains an access token from the Authorization Server.  
4. The Client uses the access token to access protected resources from the Resource Server.  

## 📘 Key Concepts in OAuth2

OAuth2 introduces several important concepts that define how authorization is handled and controlled.

### 🎯 Scopes

Scopes specify what access levels the client is requesting. They define the *boundaries of access* granted by the access token.  
Examples:
- `read:products` – allows reading product data
- `write:carts` – allows writing to the cart service

Scopes allow fine-grained permission control and can be accepted or denied by the Resource Owner.

---

### 🔑 Grant Types

Grant types define how a client can obtain an access token. Each use case (web app, backend service, mobile app) uses a different grant type:

- **Authorization Code**: Secure flow for apps with a backend server. Requires user login and redirection.
- **Implicit**: Simplified flow for browser apps (now discouraged for security reasons).
- **Resource Owner Password Credentials**: The user provides their username and password directly to the client (not recommended).
- **Client Credentials**: Used in service-to-service (machine-to-machine) scenarios without user involvement.
- **Refresh Token**: Allows obtaining new tokens without re-authenticating the user.

---

### 🪙 Access Tokens

Access tokens are short-lived credentials issued by the Authorization Server. They are typically formatted as **JWTs (JSON Web Tokens)**, containing:
- User identity
- Granted scopes
- Expiration time
- Issuer information

The **Resource Server** uses these tokens to validate and authorize incoming requests.

---

### 🧪 Implementation Examples

- [**Client Credentials**](docs/oauth/client-credentials.md): Learn how to secure a Resource Server using the OAuth2 `client_credentials` grant type, enabling secure service-to-service authentication without user interaction.

--- 

## Backlog

- [x] Basic auth
- [x] Auth server implementation
- [x] Oauth2 OpenIdConnect 
- [x] Oauth2 JWT client_credentials access
- [ ] Oauth2 web client strategies
- [ ] mTLS strategies
- [ ] ConfigServer Hardening
- [ ] Common attacks
- [ ] Linux Hardening
