package com.enigmacamp.tokonyadia.service;

import com.enigmacamp.tokonyadia.dto.request.LoginRequest;
import com.enigmacamp.tokonyadia.dto.request.RegisterRequest;
import com.enigmacamp.tokonyadia.dto.response.LoginResponse;
import com.enigmacamp.tokonyadia.utils.enums.Gender;

public interface AuthService {
    void register(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
}
