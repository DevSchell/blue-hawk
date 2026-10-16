package com.example.blue_hawk.application.usecase.user;

import com.example.blue_hawk.application.dto.user.UpdateUserCommand;
import com.example.blue_hawk.application.dto.user.UserOutput;
import com.example.blue_hawk.domain.entity.user.User;
import com.example.blue_hawk.domain.entity.user.UserRole;
import com.example.blue_hawk.domain.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
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
class UpdateUserUseCaseImplTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private UpdateUserUseCaseImpl useCase;

    private final UUID userId = UUID.randomUUID();
    private final User user = new User(userId, "Alice", "alice@example.com", "hash", UserRole.USER);

    @Test
    void shouldUpdateOnlyTheNameWhenEmailIsNull() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserOutput output = useCase.handle(new UpdateUserCommand(userId.toString(), "  Alice Smith ", null));

        assertEquals("Alice Smith", output.name());
        assertEquals("alice@example.com", output.email());
        verify(userRepository, never()).findByEmail(anyString());
    }

    @Test
    void shouldUpdateEmailWhenItIsNotTaken() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.findByEmail("alice.new@example.com")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserOutput output = useCase.handle(new UpdateUserCommand(userId.toString(), null, "Alice.New@Example.com"));

        assertEquals("Alice", output.name());
        assertEquals("alice.new@example.com", output.email());
    }

    @Test
    void shouldAllowKeepingTheSameEmail() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.findByEmail("alice@example.com")).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        UserOutput output = useCase.handle(new UpdateUserCommand(userId.toString(), "Alice", "alice@example.com"));

        assertEquals("alice@example.com", output.email());
    }

    @Test
    void shouldThrowConflictWhenEmailBelongsToAnotherUser() {
        User otherUser = new User(UUID.randomUUID(), "Bob", "bob@example.com", "hash", UserRole.USER);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.findByEmail("bob@example.com")).thenReturn(Optional.of(otherUser));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> useCase.handle(new UpdateUserCommand(userId.toString(), null, "bob@example.com")));

        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
        verify(userRepository, never()).save(any());
    }

    @Test
    void shouldThrowNotFoundWhenUserDoesNotExist() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> useCase.handle(new UpdateUserCommand(userId.toString(), "Alice", null)));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(userRepository, never()).save(any());
    }
}
