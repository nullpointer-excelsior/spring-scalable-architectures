

# 🔄 Basic Auth in Gateway Forwarding
In this examples when the API Gateway communicates with downstream microservices, it can inject Basic Authentication headers using a GatewayFilter. This allows internal calls to be authenticated automatically.

```java
@Component
public class MicroserviceAuthFilterFactory implements GatewayFilterFactory<MicroserviceAuthFilterFactory.Config> {

    @Override
    public GatewayFilter apply(Config config) {
        String credentials = Base64.getEncoder()
                .encodeToString((config.getUser() + ":" + config.getPass()).getBytes());

        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest().mutate()
                .headers(headers -> {
                    headers.remove("Authorization"); // Remove OAuth2 token
                    headers.add("Authorization", "Basic " + credentials); // Add Basic Auth
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

```

This setup ensures secure communication between services in a simple and effective way using standard HTTP headers.