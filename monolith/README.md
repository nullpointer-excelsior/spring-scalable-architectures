# 🏛️ Modular Monolith

This code represents a monolithic e-commerce application, specifically designed to be **scalable** and **maintainable** through well-defined **modules** and **event-driven communication**. The system follows a structured approach to separate concerns while maintaining a unified deployment. Below is a breakdown of its major components:


## 📂 General Structure:

### 🏗️ Monolithic Application:
The code is organized into a single project, meaning all components (cart, order, payment, shipping, products) are within the same application.

### 🏛️ Architecture:
- The application follows a layered architecture, separating business logic (services), persistence layer (entities and repositories), presentation layer (REST controllers), and integration layer (events).
- Uses **Spring Boot** for configuration and dependency management.
- **Events:**
  - Utilizes an **EventBus** for asynchronous communication between components, reducing tight coupling.
  - Events are defined in the **shared/integration** package.
  - This allows processes like order creation, payment processing, and shipping to happen asynchronously.

## 🔧 Core Components:

### 🛒 Cart:
- Allows users to create and manage shopping carts.
- Supports cart creation, updates, and retrieval.
- Uses **CartEntity, CartProductEntity, CartUserEntity** for persistence.
- Mappers (**CartMapper, CartProductMapper, CartUserMapper**) convert between entities and models.
- REST Controller (**CartRestController**) exposes cart-related endpoints.
- Service (**CartService**) contains business logic.

### 📦 Orders:
- Manages order creation from shopping carts.
- Includes order creation and state management.
- Uses **OrderEntity, OrderProductEntity** for persistence.
- Mappers (**OrderMapper, OrderProductMapper**) convert between entities and models.
- Service (**OrderService**) contains business logic.
- Event listener (**OrderListener**) reacts to order creation events.

### 💳 Payments:
- Processes payments for orders.
- Validates payment methods and creates payment records.
- Uses **PaymentEntity** for persistence.
- REST Controller (**PaymentMethodRestController**) exposes payment-related endpoints.
- Service (**PaymentService**) contains business logic.
- Event listener (**PaymentEventListener**) reacts to payment creation events.

### 🏬 Products:
- Manages product information and inventory.
- Updates stock quantities after purchases.
- Uses **ProductEntity** for persistence.
- REST Controller (**ProductRestController**) exposes product-related endpoints.
- Service (**ProductService**) contains business logic.
- Event listener (**ProductListener**) reacts to stock update events.

### 🛍️ Purchase:
- Coordinates the entire checkout process.
- Handles order creation, payment processing, and shipping.
- Uses **PurchaseEntity, PurchaseRequestEntity** for persistence.
- REST Controller (**PurchaseRestController**) exposes purchase-related endpoints.
- Service (**PurchaseProcessCoordinatorService**) orchestrates the purchase workflow.
- Event listener (**PurchaseProcessEventListener**) reacts to purchase process events.

### 🚚 Shipping:
- Manages order shipping processes.
- Creates shipping records and updates shipment status.
- Uses **ShippingEntity, DeliveryEntity** for persistence.
- REST Controller (**ShippingRestController**) exposes shipping-related endpoints.
- Service (**ShippingService**) contains business logic.
- Event listener (**ShippingListener**) reacts to shipping creation events.

## 🔗 Events & Integration (Shared/Integration):
- Defines events for asynchronous communication between components.
- Uses an **EventBus** for event distribution.
- Defines events for order creation, payments, shipping, and product stock updates.
- Enables **loose coupling** between services by using event-driven communication.
- **ApplicationEventBus** is the EventBus implementation.

## 🛒 General Purchase Flow:
1. **Cart Creation**: A user creates a cart and adds products.
2. **Checkout Process**: The user initiates checkout.
3. **Order Creation**: An order is generated from the cart.
4. **Payment Processing**: The payment is processed.
5. **Stock Update**: The stock is updated for purchased products.
6. **Shipping Creation**: A shipping record is created for the order.
7. **Purchase Completion**: The purchase process is finalized.

## ✅ Summary
This monolithic application provides a complete e-commerce checkout and purchase process, utilizing **Spring Boot**, **JPA** for persistence, and an **event bus** for asynchronous communication.

## 🔧 Running the Project

Execute the project with Gradle:

```bash

# tests
./gradlew test

# run app
./gradlew bootRun

```

## 📌 BACKLOG
- [x] feat: create endpoints **CartRestController** POST /carts/{id}/products/{sku} DELETE /carts/{id}/products/{sku}
- [x] feat: fake sleep for payment-methods/validate
- [x] feat: fake sleep for payment-process
- [x] feat: simulate error payment
- [x] feat: telemetry

