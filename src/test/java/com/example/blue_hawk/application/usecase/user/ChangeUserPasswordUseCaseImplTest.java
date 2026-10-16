package com.example.blue_hawk.application.usecase.user;

import com.example.blue_hawk.application.dto.user.ChangeUserPasswordCommand;
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

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChangeUserPasswordUseCaseImplTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private ChangeUserPasswordUseCaseImpl useCase;

    private final UUID userId = UUID.randomUUID();
    private final User user = new User(userId, "Alice", "alice@example.com", "old-hash", UserRole.USER);

    @Test
    void shouldSaveHashOfNewPasswordWhenCurrentPasswordIsCorrect() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("current-password", "old-hash")).thenReturn(true);
        when(passwordEncoder.encode("new-password")).thenReturn("new-hash");

        useCase.handle(new ChangeUserPasswordCommand(userId.toString(), "current-password", "new-password"));

        ArgumentCaptor<User> saved = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(saved.capture());
        assertEquals("new-hash", saved.getValue().getPasswordHash());
    }

    @Test
    void shouldThrowBadRequestWhenCurrentPasswordIsWrong() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("wrong-password", "old-hash")).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> useCase.handle(new ChangeUserPasswordCommand(userId.toString(), "wrong-password", "new-password")));

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
        verify(passwordEncoder, never()).encode(anyString());
        verify(userRepository, never()).save(any());
    }

    @Test
    void shouldThrowNotFoundWhenUserDoesNotExist() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> useCase.handle(new ChangeUserPasswordCommand(userId.toString(), "current-password", "new-password")));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(userRepository, never()).save(any());
    }
}
