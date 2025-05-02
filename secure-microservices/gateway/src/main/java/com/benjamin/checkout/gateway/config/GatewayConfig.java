package com.benjamin.checkout.gateway.config;

import com.benjamin.checkout.gateway.filters.MicroserviceAuthFilterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private ServicesConfig servicesConfig;
    @Autowired
    private MicroserviceAuthFilterFactory microserviceAuthFilterFactory;

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {

        var products = servicesConfig.getProductsMs();
        var carts = servicesConfig.getCartsMs();

        return builder.routes()
                .route("product-service", r -> r.path("/products/**")
                        .filters(f -> f
                                .filter(microserviceAuthFilterFactory.apply(new MicroserviceAuthFilterFactory.Config(products.getUser(), products.getPass())))
                                .circuitBreaker(c -> c.setName("product-ms-circuit-breaker")
                                .setFallbackUri("forward:/legacy/products")))
                        .uri(servicesConfig.getProductsMs().getUrl())
                )
                .route("cart-service", r -> r.path("/carts/**")
                        .filters(f -> f
                                .filter(microserviceAuthFilterFactory.apply(new MicroserviceAuthFilterFactory.Config(carts.getUser(), carts.getPass())))
                                .circuitBreaker(c -> c.setName("legacy-circuit-breaker")
                                .setFallbackUri("forward:/fallback/unavailable")
                                .addStatusCode("INTERNAL_SERVER_ERROR")))
                        .uri(carts.getUrl())
                )
                .build();
    }
}
