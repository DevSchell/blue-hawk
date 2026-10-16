package com.example.blue_hawk.application.usecase.user;

import com.example.blue_hawk.application.dto.user.CreateUserCommand;
import com.example.blue_hawk.application.dto.user.UserOutput;
import com.example.blue_hawk.domain.entity.user.User;
import com.example.blue_hawk.domain.entity.user.UserRole;
import com.example.blue_hawk.domain.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseImplTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private CreateUserUseCaseImpl useCase;

    @Test
    void shouldHashPasswordNormalizeEmailAndSaveUser() {
        when(userRepository.existsByEmail("alice@example.com")).thenReturn(false);
        when(passwordEncoder.encode("password123")).thenReturn("hashed-password");
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserOutput output = useCase.handle(new CreateUserCommand("  Alice ", " Alice@Example.COM ", "password123"));

        ArgumentCaptor<User> saved = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(saved.capture());
        assertEquals("hashed-password", saved.getValue().getPasswordHash());
        assertEquals(UserRole.USER, saved.getValue().getRole());

        assertNotNull(output.id());
        assertEquals("Alice", output.name());
        assertEquals("alice@example.com", output.email());
        assertEquals("USER", output.role());
    }

    @Test
    void shouldThrowConflictWhenEmailIsAlreadyInUse() {
        when(userRepository.existsByEmail("alice@example.com")).thenReturn(true);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> useCase.handle(new CreateUserCommand("Alice", "alice@example.com", "password123")));

        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any());
    }
}
