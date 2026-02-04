package com.enigmacamp.tokonyadia.repository;

import com.enigmacamp.tokonyadia.entity.Member;
import com.enigmacamp.tokonyadia.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByMemberId(UUID memberId);
    void deleteByToken(String token);
}
