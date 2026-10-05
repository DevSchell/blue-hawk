package com.example.blue_hawk.infrastructure.mapper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;

@Entity
@Table(name = "boardgame")
@Getter
@Setter
@NoArgsConstructor
public class BoardgameModel {
    @Id
    @JdbcTypeCode(Types.CHAR)
    @Column(name = "id", nullable = false, updatable = false, length = 36)
    private UUID id;

    @JdbcTypeCode(Types.CHAR)
    @Column(name = "name", nullable = false)
    private String name;

    @JdbcTypeCode(Types.CHAR)
    @Column(name = "description", nullable = false)
    private String description;

    @JdbcTypeCode(Types.INTEGER)
    @Column(name = "release_year")
    private int releaseYear;


    @JdbcTypeCode(Types.CHAR)
    @Column(name = "player_number")
    private String playerNumber;


    @JdbcTypeCode(Types.INTEGER)
    @Column(name = "play_time")
    private int playTime;
}
