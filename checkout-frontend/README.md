# Checkout Frontend - Angular 19

This project is an Angular 19 application simulating a checkout process. It's designed to experiment with different scalable architectures in conjunction with a Spring framework. The application utilizes NGXS for state management and provides a multi-step checkout experience.

## Project Structure

The project is structured into several key areas:

-   **`src/app/`**: Contains the core application logic.
    -   **`app.component.*`**: The root component of the application.
    -   **`app.routes.ts`**: Defines the application's routing configuration.
    -   **`app.config.ts`**: Configures the Angular application, including providers for routing and NGXS store.
    -   **`core/`**: Contains core modules, services, and models.
        -   **`utils/`**: Utility functions for data manipulation.
        -   **`models/`**: Data models used throughout the application.
        -   **`services/`**: Core services, such as the `CartService`.
        -   **`store/`**: NGXS store configurations, including state and actions.
    -   **`features/checkout/`**: Contains the checkout feature components and services.
        -   **`components/`**: Individual components for each step of the checkout process.
        -   **`services/`**: Services specific to the checkout feature, such as `CheckoutService` and `FormFactoryService`.
    -   **`shared/`**: Shared components and directives.
        -   **`directives/`**: Custom directives like `border-indicator.directive.ts` and `checkout-button.directive.ts`.
        -   **`components/`**: Reusable components like `input-text.component.ts`.

## Key Features

-   **Multi-Step Checkout**: The application simulates a checkout process with distinct steps: Shipping, Billing, and Confirmation.
-   **NGXS State Management**: Utilizes NGXS for managing application state, including cart, user, checkout, and UI state.
-   **Reactive Forms**: Uses Angular Reactive Forms for handling form inputs and validation.
-   **Dynamic Cart Management**: Allows users to add, update, and remove items from the cart.
-   **Mock Data**: Includes mock data for products and users to simulate real-world scenarios.
-   **Random Checkout Creation**: Provides functionality to create a random checkout for testing purposes.
-   **Custom Directives and Components**: Includes custom directives and components for enhanced UI and functionality.

## File Highlights

-   **`app.routes.ts`**: Configures the routes for the shipping, billing, and confirmation components.
-   **`app.config.ts`**: Sets up the NGXS store with states for cart, user, checkout, and UI, and enables Redux DevTools.
-   **`core/store/`**: Manages application state using NGXS, with separate states for cart, user, checkout, and UI.
-   **`features/checkout/components/`**: Contains components for each checkout step, including shipping, billing, and confirmation.
-   **`features/checkout/services/`**: Provides services for managing checkout-related logic, such as form creation and data handling.
-   **`shared/`**: Includes reusable components and directives used throughout the application.

## Getting Started

1.  **Clone the repository.**
2.  **Navigate to the `checkout-frontend` directory.**
3.  **Install dependencies:** `npm install`
4.  **Run the application:** `ng serve`
5.  **Open your browser and navigate to `http://localhost:4200/`.**

## Dependencies

-   Angular 19
-   NGXS
-   TailwindCSS

## Usage

The application simulates a typical checkout process. Users can navigate through the shipping, billing, and confirmation steps, filling out forms and reviewing their order. The NGXS store manages the application state, allowing for easy data manipulation and retrieval.

