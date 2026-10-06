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

@Entity
@Table(name = "match")
@Getter
@Setter
@NoArgsConstructor
public class MatchModel {

    @Id
    @JdbcTypeCode(Types.CHAR)
    @Column(name = "id", nullable = false, updatable = false, length = 36)
    private UUID uuid;

    @JdbcTypeCode(Types.CHAR)
    @Column(name = "board_game_id", nullable = false, length = 36)
    private UUID boardgameId;

    @JdbcTypeCode(Types.CHAR)
    @Column(name = "user_id", nullable = false, length = 36)
    private UUID userId;
}
