package com.example.gateway.config;

import com.example.gateway.filters.MyGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {


    @Bean
    RouteLocator handleRoutes(RouteLocatorBuilder builder, MyGatewayFilter custom) {

        return builder.routes()
                .route(p -> p.path("/api/v1/auth/**").uri("lb://JWT-AUTH-SERVICE"))
                .route(p -> p.path("/api/v1/customers/**")
                        .filters(f -> f.filter(custom) )
                        .uri("lb://DATA-JPA-DSL-MULTIPART"))
                .route(p -> p.path("/api/v1/invoices/**").filters(f->f.filter(custom))
                        .uri("lb://INVOICE-SERVICE"))
                .build();


    }
}
