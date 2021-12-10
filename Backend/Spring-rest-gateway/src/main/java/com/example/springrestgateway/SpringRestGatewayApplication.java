package com.example.springrestgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringRestGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("users", r -> r
                        .host("localhost:8080")
                        .and()
                        .path("/api/users/{login}", "/api/users")
                        .uri("http://localhost:8081"))
                .route("characters", r -> r
                        .host("localhost:8080")
                        .and()
                        .path("/api/champions", "/api/champions/**", "/api/users/{login}/champions", "/api/users/{login}/champions/**")
                        .uri("http://localhost:8082"))
                .build();
    }
}
