package com.benjamin.checkout.gateway.restcontrollers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackRestController {

    @GetMapping("fallback/unavailable")
    public Mono<Response> fallback() {
        return Mono.just(new Response("Service gateway status","Checkout Service temporarily unavailable. Please try again later."));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Response {
        private String title;
        private String message;
    }
}
