package com.example.gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class MyGlobalFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        Mono<String> name = Mono.just("Sriram");


        name.subscribe(System.out::println);

        Flux<String> names = Flux.just("Sriram","RaguRam","Kamal","Vimal","Bimal");

        names.subscribe(System.out::println);


        System.out.println("==========>" +exchange.getRequest().getURI());

        return chain.filter(exchange);
    }

}
