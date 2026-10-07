package com.example.blue_hawk.application.usecase.user;

import com.example.blue_hawk.application.dto.user.ListUserQuery;
import com.example.blue_hawk.application.dto.user.UserOutput;
import com.example.blue_hawk.domain.entity.user.User;
import com.example.blue_hawk.domain.entity.user.UserRole;
import com.example.blue_hawk.domain.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListUserUseCaseImplTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private ListUserUseCaseImpl useCase;

    @Test
    void shouldUseDefaultPagingAndMapUsersToOutput() {
        UUID aliceId = UUID.randomUUID();
        UUID bobId = UUID.randomUUID();
        Page<User> page = new PageImpl<>(List.of(
                new User(aliceId, "Alice", "alice@example.com", "hash", UserRole.USER),
                new User(bobId, "Bob", "bob@example.com", "hash", UserRole.ADMIN)));
        when(userRepository.findAll(null, null, 0, 20)).thenReturn(page);

        List<UserOutput> output = useCase.handle(new ListUserQuery(null, null, null, null));

        assertEquals(List.of(
                new UserOutput(aliceId.toString(), "Alice", "alice@example.com", "USER"),
                new UserOutput(bobId.toString(), "Bob", "bob@example.com", "ADMIN")), output);
    }

    @Test
    void shouldClampPageAndSizeAndPassFilters() {
        when(userRepository.findAll("bo", "example", 0, 100)).thenReturn(Page.empty());

        List<UserOutput> output = useCase.handle(new ListUserQuery("bo", "example", -1, 500));

        assertEquals(List.of(), output);
    }

    @Test
    void shouldUseMinimumSizeOfOne() {
        when(userRepository.findAll(null, null, 2, 1)).thenReturn(Page.empty());

        List<UserOutput> output = useCase.handle(new ListUserQuery(null, null, 2, 0));

        assertEquals(List.of(), output);
    }
}
