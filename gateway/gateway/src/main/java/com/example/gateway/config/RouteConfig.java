package com.example.gateway.config;

import com.example.gateway.filters.MyGatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {


    @Bean
    RouteLocator handleRoutes(RouteLocatorBuilder builder, MyGatewayFilter gatewayFilter) {

        return builder.routes()
                .route(p -> p.path("/v1/auth/**")
                        .uri("lb://USER-MANAGEMENT"))

                .route(p -> p.path("/v1/users/**", "/v1/products/**", "/v1/laptops/**")
                        .filters(f -> f.filter(gatewayFilter))
                        .uri("lb://USER-MANAGEMENT"))

                .route(p -> p.path("/v1/product/**")
                        .filters(f->f.filter(gatewayFilter))
                        .uri("lb://PRODUCT-SERVICE"))

                .route(p -> p.path("/v1/orders/**")
                        .filters(f->f.filter(gatewayFilter))
                        .uri("lb://ORDER-SERVICE"))

                .build();


    }
}
