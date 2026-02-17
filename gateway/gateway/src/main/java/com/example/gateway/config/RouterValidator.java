package com.example.gateway.config;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouterValidator {
        public static final List<String> whiteLabels = List.of("/v1/auth/register", "/v1/auth/login");

        public Predicate<ServerHttpRequest> isSecured = request -> whiteLabels.stream()
                .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
