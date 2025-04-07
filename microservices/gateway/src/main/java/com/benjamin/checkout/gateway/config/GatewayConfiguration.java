package com.benjamin.checkout.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GatewayConfiguration {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("productService", r -> r.path("/products/**")
                        .uri("http://localhost:8081")
                )
                .route("cartService", r -> r.path("/carts/**")
                        .uri("http://localhost:8082")
                )
                .build();
    }

}
