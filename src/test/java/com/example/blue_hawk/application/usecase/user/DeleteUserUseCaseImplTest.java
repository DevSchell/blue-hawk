package com.example.blue_hawk.application.usecase.user;

import com.example.blue_hawk.application.dto.user.DeleteUserCommand;
import com.example.blue_hawk.domain.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteUserUseCaseImplTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private DeleteUserUseCaseImpl useCase;

    private final UUID userId = UUID.randomUUID();

    @Test
    void shouldPhysicallyDeleteUserWhenItExists() {
        when(userRepository.existsById(userId)).thenReturn(true);

        useCase.handle(new DeleteUserCommand(userId.toString()));

        verify(userRepository).deleteById(userId);
    }

    @Test
    void shouldThrowNotFoundWhenUserDoesNotExist() {
        when(userRepository.existsById(userId)).thenReturn(false);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> useCase.handle(new DeleteUserCommand(userId.toString())));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        verify(userRepository, never()).deleteById(any());
    }
}
