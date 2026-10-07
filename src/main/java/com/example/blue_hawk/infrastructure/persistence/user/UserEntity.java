package com.example.blue_hawk.infrastructure.persistence.user;

import com.example.blue_hawk.domain.entity.user.User;
import com.example.blue_hawk.domain.entity.user.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

// Crases preservam a caixa do nome "User" (MySQL em Linux diferencia maiúsculas em nomes de tabela).
@Entity
@Table(name = "`user`")
public class UserEntity {

    // A migration define id como CHAR(36); sem isso o Hibernate usaria BINARY(16).
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", nullable = false, updatable = false, length = 36)
    private UUID id;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    // Hash BCrypt, nunca a senha em texto puro.
    @Column(name = "password", nullable = false, length = 150)
    private String password;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role", nullable = false)
    private UserRole role;

    protected UserEntity() {
    }

    public static UserEntity fromDomain(User user) {
        UserEntity entity = new UserEntity();
        entity.id = user.getId();
        entity.name = user.getName();
        entity.email = user.getEmail();
        entity.password = user.getPasswordHash();
        entity.role = user.getRole();
        return entity;
    }

    public User toDomain() {
        return new User(id, name, email, password, role);
    }
}
