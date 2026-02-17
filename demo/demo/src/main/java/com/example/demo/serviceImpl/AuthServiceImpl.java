package com.example.demo.serviceImpl;

import com.example.demo.jwt.JwtGeneratorUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl {

    private final AuthenticationManager authenticationManager;
    private final JwtGeneratorUtil jwtGeneratorUtil;

    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtGeneratorUtil jwtGeneratorUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtGeneratorUtil = jwtGeneratorUtil;
    }

    public String authenticate(String username, String password) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(username, password)
                );

        return jwtGeneratorUtil.generateToken(authentication.getName());
    }

}
