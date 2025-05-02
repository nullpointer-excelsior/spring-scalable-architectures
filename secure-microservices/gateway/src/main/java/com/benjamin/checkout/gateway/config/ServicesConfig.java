package com.benjamin.checkout.gateway.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "services")
public class ServicesConfig {
    private ServiceConfig productsMs;
    private ServiceConfig cartsMs;
    @Getter
    @Setter
    public static class ServiceConfig {
        private String url;
        private String user;
        private String pass;
    }
}
