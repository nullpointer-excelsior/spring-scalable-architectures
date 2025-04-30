package com.benjamin.ecommerce;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CheckoutApplication {

    @Value("${eureka.client.enabled:true}") // default en true si no se encuentra
    private boolean eurekaEnabled;

    public static void main(String[] args) {
        SpringApplication.run(CheckoutApplication.class, args);
    }

    @PostConstruct
    public void logEurekaConfig() {
        System.out.println("🔍 ¿Eureka habilitado?: " + eurekaEnabled);
    }
}
