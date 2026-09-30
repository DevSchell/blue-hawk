package com.example.blue_hawk.infrastructure.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("""
            select u from UserEntity u
            where (:name is null or lower(u.name) like lower(concat('%', :name, '%')))
              and (:email is null or lower(u.email) like lower(concat('%', :email, '%')))
            """)
    Page<UserEntity> search(@Param("name") String name, @Param("email") String email, Pageable pageable);
}
