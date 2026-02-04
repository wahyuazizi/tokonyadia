package com.enigmacamp.tokonyadia.service.Implementation;

import com.enigmacamp.tokonyadia.entity.Member;
import com.enigmacamp.tokonyadia.entity.RefreshToken;
import com.enigmacamp.tokonyadia.repository.RefreshTokenRepository;
import com.enigmacamp.tokonyadia.service.MemberService;
import com.enigmacamp.tokonyadia.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Value("${jwt.refresh.token.expiration}")
    private Long refreshTokenExpiration;

    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberService memberService;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, MemberService memberService) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.memberService = memberService;
    }

    @Override
    public RefreshToken createRefreshToken(UUID memberId) {

        refreshTokenRepository.findByMemberId(memberId)
                .ifPresent(refreshTokenRepository::delete);

        RefreshToken token = new RefreshToken();
        token.setMember(memberService.getMemberById(memberId));
        token.setToken(UUID.randomUUID().toString());
        token.setExpiresAt(Instant.now().plusMillis(refreshTokenExpiration));
        return refreshTokenRepository.save(token);
    }

    @Override
    public Boolean isRefreshTokenExpired(RefreshToken refreshToken) {

        return refreshToken.getExpiresAt().isBefore(Instant.now());
    }

    @Override
    public Optional<RefreshToken> getRefreshTokenByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    @Override
    public void deleteRefreshToken(RefreshToken refreshToken) {
        refreshTokenRepository.delete(refreshToken);
    }
}
