package com.example.blue_hawk.domain.repository;

import com.example.blue_hawk.domain.entity.user.User;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {

    User save(User user);

    Optional<User> findById(UUID userId);

    Optional<User> findByEmail(String email);

    Page<User> findAll(String name, String email, int page, int size);

    void deleteById(UUID userId);

    boolean existsById(UUID userId);

    boolean existsByEmail(String email);
}
