
# 🛠️ Server Configuration

1. **Add Spring Security dependency:**

```kts
dependencies {
	implementation("org.springframework.boot:spring-boot-starter-security")
    // other deps...
}
```
2. Configure default user credentials in `application.properties`:

```properties
spring.security.user.name=products_user
spring.security.user.password=securepass
spring.security.user.roles=SERVICE
```

3. Secure your endpoints with a security filter:

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // APIs doesn't need protections agains csrf attacks
                .httpBasic(Customizer.withDefaults()) // Adding basic authentication
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/actuator/**").permitAll() // permit only health endpoints
                        .anyRequest().authenticated() // secure all other endpoints 
                );

        return http.build();
    }

}
```

## 📥 Client Requests

Send Basic Auth credentials using the `-u` flag with `curl`:

```shell
curl -u products_user:securepass http://localhost:8080/products
```