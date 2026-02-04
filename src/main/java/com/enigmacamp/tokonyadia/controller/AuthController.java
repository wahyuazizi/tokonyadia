package com.enigmacamp.tokonyadia.controller;


import com.enigmacamp.tokonyadia.dto.request.LoginRequest;
import com.enigmacamp.tokonyadia.dto.response.LoginResponse;
import com.enigmacamp.tokonyadia.security.jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
//        System.out.println("Bcript: " + new BCryptPasswordEncoder().encode("admin123"));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(), loginRequest.password()
                )
        );

        String token = jwtUtil.generateToken(authentication.getName());
        return new LoginResponse(token);
    }
}
