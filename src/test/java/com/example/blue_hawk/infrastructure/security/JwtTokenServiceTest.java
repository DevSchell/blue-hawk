package com.example.blue_hawk.infrastructure.security;

import com.example.blue_hawk.domain.entity.user.User;
import com.example.blue_hawk.domain.entity.user.UserRole;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;

import java.time.Duration;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JwtTokenServiceTest {

    private static final String SECRET = "0123456789abcdef0123456789abcdef-test";

    // Usa os mesmos beans de encoder/decoder da aplicação, sem subir o contexto do Spring.
    private final SecurityConfig securityConfig = new SecurityConfig();
    private final JwtTokenService tokenService = new JwtTokenService(securityConfig.jwtEncoder(SECRET), 3600);
    private final JwtDecoder jwtDecoder = securityConfig.jwtDecoder(SECRET);

    private final User user = new User(UUID.randomUUID(), "Alice", "alice@example.com", "hash", UserRole.ADMIN);

    @Test
    void shouldGenerateTokenWithUserIdRoleAndExpiration() {
        Jwt jwt = jwtDecoder.decode(tokenService.generateToken(user));

        assertEquals(user.getId().toString(), jwt.getSubject());
        assertEquals("ADMIN", jwt.getClaimAsString("scope"));
        assertEquals(Duration.ofSeconds(3600), Duration.between(jwt.getIssuedAt(), jwt.getExpiresAt()));
        assertEquals(3600, tokenService.getExpirationSeconds());
    }

    @Test
    void shouldRejectTokenSignedWithAnotherSecret() {
        JwtTokenService otherService = new JwtTokenService(
                securityConfig.jwtEncoder("another-secret-with-at-least-32-bytes!"), 3600);

        String foreignToken = otherService.generateToken(user);

        assertThrows(JwtException.class, () -> jwtDecoder.decode(foreignToken));
    }
}
