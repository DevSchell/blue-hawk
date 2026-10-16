package com.example.blue_hawk.application.usecase.user;

import com.example.blue_hawk.application.dto.user.GetUserQuery;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserUseCaseImplTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private GetUserUseCaseImpl useCase;

    private final UUID userId = UUID.randomUUID();

    @Test
    void shouldReturnUserWhenItExists() {
        User user = new User(userId, "Alice", "alice@example.com", "hash", UserRole.ADMIN);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserOutput output = useCase.handle(new GetUserQuery(userId.toString()));

        assertEquals(new UserOutput(userId.toString(), "Alice", "alice@example.com", "ADMIN"), output);
    }

    @Test
    void shouldThrowNotFoundWhenUserDoesNotExist() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> useCase.handle(new GetUserQuery(userId.toString())));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
}
