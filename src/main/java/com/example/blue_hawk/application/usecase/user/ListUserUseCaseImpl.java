package com.example.blue_hawk.application.usecase.user;

import com.example.blue_hawk.application.dto.user.ListUserQuery;
import com.example.blue_hawk.application.dto.user.UserOutput;
import com.example.blue_hawk.domain.repository.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListUserUseCaseImpl implements ListUserUseCase {

    private final IUserRepository userRepository;

    public ListUserUseCaseImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserOutput> handle(ListUserQuery query) {
        int page = query.page() == null ? 0 : Math.max(query.page(), 0);
        int size = query.size() == null ? 20 : Math.clamp(query.size(), 1, 100);

        return userRepository.findAll(query.name(), query.email(), page, size)
                .map(UserOutput::from)
                .toList();
    }
}
