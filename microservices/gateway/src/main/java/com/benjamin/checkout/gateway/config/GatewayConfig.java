package com.benjamin.checkout.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GatewayConfig {

    @Value("${ecommerce.checkout.products.url}")
    private String productServiceUrl;

    @Value("${ecommerce.checkout.cart.url}")
    private String cartServiceUrl;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("product-service", r -> r.path("/products/**")
                        .uri(productServiceUrl)
                )
                .route("cart-service", r -> r.path("/carts/**")
                        .uri(cartServiceUrl)
                )
                .build();
    }

}
