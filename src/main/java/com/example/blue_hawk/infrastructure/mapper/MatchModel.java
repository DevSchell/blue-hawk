package com.example.blue_hawk.infrastructure.mapper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "match")
@Getter
@Setter
@NoArgsConstructor

public class MatchModel {
    @Id
    @Column(name = "id", nullable = false, updatable = false, length = 36)
    private UUID uuid;

    @Column(name = "board_game_id", nullable = false, length = 36)
    private UUID boardgameId;
}
