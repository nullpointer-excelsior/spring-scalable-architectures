package com.benjamin.checkout.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GatewayConfig {

    @Value("${legacy.url}")
    private String legacyUrl;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product-service", r -> r.path("/products/**")
                        .filters(f -> f.circuitBreaker(c -> c.setName("product-ms-circuit-breaker")
                                .setFallbackUri("forward:/legacy/products")))
                        .uri("lb://products-ms")
                )
                .route("cart-service", r -> r.path("/carts/**")
                        .filters(f -> f.circuitBreaker(c -> c.setName("carts-circuit-breaker")
                                .setFallbackUri("forward:/fallback/unavailable")
                                .addStatusCode("INTERNAL_SERVER_ERROR")))
                        .uri("lb://cart-ms")
                )
                .route("product-legacy", r -> r.path("/legacy/products/**")
                        .filters(f -> f.rewritePath("/legacy/products", "/products")
                                .circuitBreaker(c -> c.setName("legacy-circuit-breaker")
                                        .setFallbackUri("forward:/fallback/unavailable")
                                        .addStatusCode("INTERNAL_SERVER_ERROR"))
                        )
                        .uri(legacyUrl))
                .build();
    }

}
