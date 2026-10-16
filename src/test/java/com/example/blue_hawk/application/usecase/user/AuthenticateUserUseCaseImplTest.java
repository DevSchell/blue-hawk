package com.example.blue_hawk.application.usecase.user;

import com.example.blue_hawk.application.dto.user.AuthenticateUserCommand;
import com.example.blue_hawk.application.dto.user.AuthenticateUserOutput;
import com.example.blue_hawk.domain.entity.user.User;
import com.example.blue_hawk.domain.entity.user.UserRole;
import com.example.blue_hawk.domain.repository.IUserRepository;
import com.example.blue_hawk.infrastructure.security.JwtTokenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthenticateUserUseCaseImplTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenService tokenService;

    @InjectMocks
    private AuthenticateUserUseCaseImpl useCase;

    private final User user = new User(UUID.randomUUID(), "Alice", "alice@example.com", "stored-hash", UserRole.USER);

    @Test
    void shouldReturnTokenWhenCredentialsAreValid() {
        when(userRepository.findByEmail("alice@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("password123", "stored-hash")).thenReturn(true);
        when(tokenService.generateToken(user)).thenReturn("jwt-token");
        when(tokenService.getExpirationSeconds()).thenReturn(3600L);

        AuthenticateUserOutput output = useCase.handle(new AuthenticateUserCommand("Alice@Example.com", "password123"));

        assertEquals("jwt-token", output.accessToken());
        assertEquals("Bearer", output.tokenType());
        assertEquals(3600L, output.expiresIn());
    }

    @Test
    void shouldThrowUnauthorizedWhenEmailDoesNotExist() {
        when(userRepository.findByEmail("nobody@example.com")).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> useCase.handle(new AuthenticateUserCommand("nobody@example.com", "password123")));

        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
        verifyNoInteractions(tokenService);
    }

    @Test
    void shouldThrowUnauthorizedWhenPasswordIsWrong() {
        when(userRepository.findByEmail("alice@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong-password", "stored-hash")).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> useCase.handle(new AuthenticateUserCommand("alice@example.com", "wrong-password")));

        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatusCode());
        verifyNoInteractions(tokenService);
    }
}
