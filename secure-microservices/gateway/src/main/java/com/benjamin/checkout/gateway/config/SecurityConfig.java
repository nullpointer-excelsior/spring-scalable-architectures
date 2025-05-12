package com.benjamin.checkout.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebFluxSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http.authorizeExchange(authorize ->
                        authorize.pathMatchers("/login*", "/").permitAll()
                                .pathMatchers("/products").permitAll()
                                .pathMatchers("/products/quantity").authenticated()
                                .pathMatchers("/carts/**").hasAuthority("SCOPE_carts.read")
                                .anyExchange()
                                .authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()))
                .build();
    }

}
