# 🚀 Scalable High-Performing Architectures with Spring

This repository contains various architectural styles to create highly scalable, maintainable, and high-performing applications based on a checkout-app and Spring.

## 🖥️ [Frontend Client](checkout-frontend/README.md)
To test the different architectures, you can run the **checkout-frontend** application, which interacts with the backend service you wish to try. 🚀

## 🏛️ [Modular Monolith](monolith/README.md)

In this architectural style, all components are developed within the same application. This results in a single deployable unit, which simplifies development and deployment but may present challenges in scalability and maintainability as the application grows. Due to this limitation, we can implement some architectural patterns to achieve a modular and scalable approach.

## 🔗 [Microservices](microservices/README.md)

In this architectural style, the application is decomposed into multiple independently deployable services. Each microservice is responsible for a specific business capability and communicates with other services via APIs. This approach enhances scalability, maintainability, and flexibility but introduces challenges in inter-service communication, data consistency, and deployment complexity.


## 🔐 [Secure Microservices](secure-microservices/README.md)

This section focuses on securing microservices using different strategies such as Basic Authentication, OAuth2, and JWT. It explains how to protect service-to-service communication, secure endpoints through Spring Security, and implement common security patterns in distributed architectures.


## 🔧 Running the Project

1. **Start Docker Compose:**
   Navigate to the root directory of the project and run the following command to start the Docker containers:

   ```bash
   docker-compose up

   ```
2. **Run the Architecture:**
    Enter the directory of the architectural style you wish to run. For example, if you want to run the Monolith architecture, navigate to the monolith directory:

    ```bash
    ./gradlew bootRun
    ```
    Repeat this step for any other architectural style by navigating to its respective directory.

3. **Run the Frontend:**
    After starting the backend service, navigate to the checkout-frontend directory and execute the following command to run the Angular frontend:

    ```bash
    ng serve
    ```
## 👨‍💻 Credits

Developed by **Benjamín**