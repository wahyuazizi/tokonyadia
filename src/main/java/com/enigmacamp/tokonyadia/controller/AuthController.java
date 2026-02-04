package com.enigmacamp.tokonyadia.controller;


import com.enigmacamp.tokonyadia.dto.request.LoginRequest;
import com.enigmacamp.tokonyadia.dto.request.RefreshTokenRequest;
import com.enigmacamp.tokonyadia.dto.request.RegisterRequest;
import com.enigmacamp.tokonyadia.dto.response.LoginResponse;
import com.enigmacamp.tokonyadia.entity.Member;
import com.enigmacamp.tokonyadia.entity.RefreshToken;
import com.enigmacamp.tokonyadia.entity.Role;
import com.enigmacamp.tokonyadia.security.jwt.JwtUtil;
import com.enigmacamp.tokonyadia.service.AuthService;
import com.enigmacamp.tokonyadia.service.RefreshTokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService  authService;
    private final RefreshTokenService refreshTokenService;
    private final JwtUtil jwtUtils;

    public AuthController(AuthService authService, RefreshTokenService refreshTokenService, JwtUtil jwtUtils) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest){
        authService.register(registerRequest);
        return ResponseEntity.ok("Register Successfully");
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refreshToken(
            @RequestBody RefreshTokenRequest request
    ){
        String requestToken = request.refreshToken();

        RefreshToken refreshToken = refreshTokenService
                .getRefreshTokenByToken(requestToken)
                .orElseThrow(() -> new RuntimeException("Refresh Token not found"));

        if(refreshTokenService.isRefreshTokenExpired(refreshToken)){
            refreshTokenService.deleteRefreshToken(refreshToken);
            return ResponseEntity.badRequest()
                    .body(Map.of("message", "Refresh Token Expired, Please Login Again."));
        }

        Member member = refreshToken.getMember();

        List<String> roles = member.getRoles()
                .stream()
                .map(Role::getName)
                .toList();

        String newAccessToken = jwtUtils.generateToken(member.getUsername(), roles);

        return ResponseEntity.ok(Map.of("access-token", newAccessToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody RefreshTokenRequest request) {

        refreshTokenService.deleteRefreshToken(request.refreshToken());

        return ResponseEntity.ok("Logout success");
    }




}
