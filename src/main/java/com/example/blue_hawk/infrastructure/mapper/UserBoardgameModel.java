package com.example.blue_hawk.infrastructure.mapper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Types;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserBoardgameModel {
    private UUID id;

    private UUID userId;

    private UUID boardgameId;
}
