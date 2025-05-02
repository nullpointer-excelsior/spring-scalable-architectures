package com.benjamin.checkout.gateway.filters;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class MicroserviceAuthFilterFactory implements GatewayFilterFactory<MicroserviceAuthFilterFactory.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        String credentials = Base64.getEncoder()
                .encodeToString((config.getUser() + ":" + config.getPass()).getBytes());

        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest().mutate()
                    .headers(headers -> {
                        headers.remove("Authorization"); // removing Oauth authorization
                        headers.add("Authorization", "Basic " + credentials); // Adding authorization for microservices
                    })
                    .build();
            return chain.filter(exchange.mutate().request(request).build());
        };
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Config {
        private String user;
        private String pass;
    }
}

