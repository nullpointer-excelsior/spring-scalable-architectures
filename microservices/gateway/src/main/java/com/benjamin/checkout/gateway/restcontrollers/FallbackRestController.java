package com.benjamin.checkout.gateway.restcontrollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackRestController {

    @GetMapping("fallback/unavailable")
    public Mono<Response> fallback() {
        return Mono.just(new Response("Service gateway status","Checkout Service temporarily unavailable. Please try again later."));
    }

    public static class Response {

        private String title;
        private String message;

        public Response(String title, String message) {
            this.title = title;
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
