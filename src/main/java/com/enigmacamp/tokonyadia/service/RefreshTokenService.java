package com.enigmacamp.tokonyadia.service;

import com.enigmacamp.tokonyadia.entity.RefreshToken;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenService {
    RefreshToken createRefreshToken(UUID memberId);
    Boolean isRefreshTokenExpired(RefreshToken refreshToken);
    Optional<RefreshToken>  getRefreshTokenByToken(String token);
    void deleteRefreshToken(RefreshToken refreshToken);
    void deleteRefreshToken(String token);
}
