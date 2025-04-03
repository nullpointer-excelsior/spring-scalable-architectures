# 🛒 Checkout Frontend

This project is an **Angular 19** application simulating a **checkout process**. It's designed to experiment with different **scalable architectures** in conjunction with a **Spring framework**. The application utilizes **NGXS** for state management and provides a **multi-step checkout experience**. 🚀

## 📂 Project Structure

The project is structured into several key areas:

-   **`src/app/`** 📁: Contains the core application logic.
    -   **`app.component.*`** 🏠: The root component of the application.
    -   **`app.routes.ts`** 🗺️: Defines the application's routing configuration.
    -   **`app.config.ts`** ⚙️: Configures the Angular application, including providers for routing and NGXS store.
    -   **`core/`** 🛠️: Contains core modules, services, and models.
        -   **`utils/`** 🔧: Utility functions for data manipulation.
        -   **`models/`** 📌: Data models used throughout the application.
        -   **`services/`** 🔄: Core services, such as the `CartService`.
        -   **`store/`** 📊: NGXS store configurations, including state and actions.
    -   **`features/checkout/`** 🏷️: Contains the checkout feature components and services.
        -   **`components/`** 🧩: Individual components for each step of the checkout process.
        -   **`services/`** 💳: Services specific to the checkout feature, such as `CheckoutService` and `FormFactoryService`.
    -   **`shared/`** 🔄: Shared components and directives.
        -   **`directives/`** 🎨: Custom directives like `border-indicator.directive.ts` and `checkout-button.directive.ts`.
        -   **`components/`** 🏗️: Reusable components like `input-text.component.ts`.

## ⭐ Key Features

-   **🚀 Multi-Step Checkout**: Simulates a checkout process with distinct steps: **Shipping, Billing, and Confirmation**.
-   **🛍️ NGXS State Management**: Uses **NGXS** to manage cart, user, checkout, and UI state.
-   **📝 Reactive Forms**: Implements Angular **Reactive Forms** for handling form inputs and validation.
-   **🛒 Dynamic Cart Management**: Users can **add, update, and remove items** from the cart.
-   **📦 Mock Data**: Includes **mock data** for products and users to simulate real-world scenarios.
-   **🎲 Random Checkout Creation**: Allows testing via random checkout creation.
-   **🎛️ Custom Directives and Components**: Enhances UI and functionality.

## 📌 File Highlights

-   **`app.routes.ts`** 🗺️: Configures the routes for the **shipping, billing, and confirmation** components.
-   **`app.config.ts`** ⚙️: Sets up the NGXS store and enables Redux DevTools.
-   **`core/store/`** 📊: Manages application state with NGXS.
-   **`features/checkout/components/`** 🧩: Components for each checkout step.
-   **`features/checkout/services/`** 🔄: Handles checkout-related logic.
-   **`shared/`** 🔄: Reusable components and directives.

## 🚀 Getting Started

1.  **Clone the repository** 🛠️  
2.  **Navigate to the `checkout-frontend` directory** 📂  
3.  **Install dependencies:** `npm install` 📥  
4.  **Run the application:** `ng serve` ▶️  
5.  **Open your browser and navigate to** `http://localhost:4200/` 🌍  

## 📦 Dependencies

-   **Angular 19** ⚡  
-   **NGXS** 🔄  
-   **TailwindCSS** 🎨  
-   **NGX-toastr** 🔔  

## 🛠️ Usage

The application simulates a **checkout process**. Users navigate through **shipping, billing, and confirmation steps**, fill out forms, and review their order. The **NGXS store** efficiently manages the application state.

## 🚀 Running the Angular 19 Application

To run the **checkout-frontend** application locally, follow these steps:

### Prerequisites
- Make sure you have **Node.js** and **npm** installed on your machine.
- Install **Angular CLI** globally if you haven't already:

```bash
# install angular cli 
npm install -g @angular/cli

# install dependecies
npm install

# start app
npm run start 

```

## 📌 Backlog

- [x] ✅ **Refactor UI state:** Separate into `checkout-steps-state`
- [x] 🆕 **Successfully payment page**
- [ ] 🎨 **Improve notification styles**
