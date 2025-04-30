package com.benjamin.checkout.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Value("${services.legacy.url}")
    private String legacyUrl; // Cambiar a service.legacy.url

    @Value("${services.products-ms.url}")
    private String productsMsUri;

    @Value("${services.cart-ms.url}")
    private String cartMsUri;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product-service", r -> r.path("/products/**")
                        .filters(f -> f.circuitBreaker(c -> c.setName("product-ms-circuit-breaker")
                                .setFallbackUri("forward:/legacy/products")))
                        .uri(productsMsUri) // Usar la propiedad inyectada
                )
                .route("cart-service", r -> r.path("/carts/**")
                        .filters(f -> f.circuitBreaker(c -> c.setName("legacy-circuit-breaker")
                                .setFallbackUri("forward:/fallback/unavailable")
                                .addStatusCode("INTERNAL_SERVER_ERROR")))
                        .uri(cartMsUri) // Usar la propiedad inyectada
                )
                .route("product-legacy", r -> r.path("/legacy/products/**")
                        .filters(f -> f.rewritePath("/legacy/products", "/products")
                                .circuitBreaker(c -> c.setName("legacy-circuit-breaker")
                                        .setFallbackUri("forward:/fallback/unavailable")
                                        .addStatusCode("INTERNAL_SERVER_ERROR"))
                        )
                        .uri(legacyUrl)) // Usar la propiedad inyectada
                .build();
    }

}
