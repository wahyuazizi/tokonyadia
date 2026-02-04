package com.enigmacamp.tokonyadia.dto.response;

public record LoginResponse(
        String token,
        String refresToken
) {
}
