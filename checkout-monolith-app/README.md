# Monolith

Este código representa una aplicación monolítica de comercio electrónico, específicamente la parte del proceso de pago y compra (checkout). Aquí hay un desglose de lo que hace cada componente principal:

## Estructura General:

### Aplicación Monolítica:
El código está organizado en un solo proyecto, lo que significa que todos los componentes (carrito, orden, pago, envío, productos) están dentro de la misma aplicación.

### Arquitectura:
La aplicación sigue una arquitectura basada en capas, separando la lógica de negocio (servicios), la capa de persistencia (entidades y repositorios), la capa de presentación (controladores REST), y la capa de integración (eventos).
Se utiliza Spring Boot para la configuración y el manejo de dependencias.
Eventos:
Utiliza un bus de eventos (EventBus) para desacoplar los componentes y permitir la comunicación asíncrona entre ellos. Esto es visible en los listeners y eventos definidos en el paquete shared/integration.
Esto permite procesos como, crear una orden, luego un pago y por ultimo un envio, todos de manera asincrona.
Componentes Principales:

### Carrito (Cart):
Permite a los usuarios crear y gestionar carritos de compras.
Incluye la creación, actualización y recuperación de carritos.
Utiliza entidades (CartEntity, CartProductEntity, CartUserEntity) para la persistencia.
Mappers (CartMapper, CartProductMapper, CartUserMapper) para convertir entre entidades y modelos.
Controlador REST (CartRestController) para exponer los endpoints del carrito.
Servicio (CartService) contiene la logica de negocio.
### Órdenes (Order):
Gestiona la creación de órdenes a partir de los carritos.
Incluye la creación de órdenes y el manejo del estado de las órdenes.
Utiliza entidades (OrderEntity, OrderProductEntity) para la persistencia.
Mappers (OrderMapper, OrderProductMapper) para convertir entre entidades y modelos.
Servicio (OrderService) contiene la logica de negocio.
Listener de eventos (OrderListener) que reacciona a los eventos de creación de órdenes.
### Pagos (Payment):
Procesa los pagos de las órdenes.
Incluye la validación de métodos de pago y la creación de registros de pago.
Utiliza entidades (PaymentEntity) para la persistencia.
Controlador REST (PaymentMethodRestController) para exponer los endpoints de pago.
Servicio (PaymentService) contiene la logica de negocio.
Listener de eventos (PaymentEventListener) que reacciona a los eventos de creación de pagos.
### Productos (Products):
Gestiona la información y el inventario de los productos.
Incluye la actualización de la cantidad de productos en stock.
Utiliza entidades (ProductEntity) para la persistencia.
Controlador REST (ProductRestController) para exponer los endpoints de productos.
Servicio (ProductService) contiene la logica de negocio.
Listener de eventos (ProductListener) que reacciona a los eventos de actualizacion de stock de productos.
### Compras (Purchase):
Coordina el proceso de compra completo.
Incluye la creación de órdenes, pagos y envíos.
Utiliza entidades (PurchaseEntity, PurchaseRequestEntity) para la persistencia.
Controlador REST (PurchaseRestController) para exponer los endpoints de compra.
Servicio (PurchaseProcessCoordinatorService) contiene la logica de orquestacion de la compra.
Listener de eventos (PurchaseProcessEventListener) que reacciona a los eventos del proceso de compra.
### Envíos (Shipping):
Gestiona el proceso de envío de las órdenes.
Incluye la creación de registros de envío y la actualización del estado de los envíos.
Utiliza entidades (ShippingEntity, DeliveryEntity) para la persistencia.
Controlador REST (ShippingRestController) para exponer los endpoints de envío.
Servicio (ShippingService) contiene la logica de negocio.
Listener de eventos (ShippingListener) que reacciona a los eventos de creación de envios.
Eventos e Integración (Shared/Integration):
Define eventos para la comunicación asíncrona entre componentes.
Incluye un bus de eventos (EventBus) para la distribución de eventos.
Define eventos específicos para la creación de órdenes, pagos, envíos y la actualización de stock de productos.
ApplicationEventBus, es la implementacion del EventBus.
Flujo de Compra General:

- Creación del Carrito: Un usuario crea un carrito y agrega productos.
- Proceso de Compra: El usuario inicia el proceso de compra.
- Creación de la Orden: Se crea una orden a partir del carrito.
- Proceso de Pago: Se procesa el pago de la orden.
- Actualización del Stock: Se actualiza el stock de los productos comprados.
- Creación del Envío: Se crea un registro de envío para la orden.
- Finalización de la Compra: Se completa el proceso de compra.

En resumen, esta aplicación monolítica proporciona una funcionalidad completa para el proceso de pago y compra de un comercio electrónico, utilizando Spring Boot, JPA para la persistencia y un bus de eventos para la comunicación asíncrona.

## BACKLOG
- [ ] bug: HibernateException: A collection with orphan deletion was no longer referenced by the owning entity instance: com.benjamin.ecommerce.cart.entities.CartEntity.products URL: PUT **/cart/{}/products**
- [ ] feat: create endpoints CartRestController POST /carts/{id}/products/{sku} DELETE /carts/{id}/products/{sku}