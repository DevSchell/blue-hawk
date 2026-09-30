package com.example.blue_hawk.infrastructure.persistence.userBoardgame;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "user_board_game")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserBoardgameJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false, length = 36)
    private UUID id;

    @Column(name = "user_id", nullable = false, length = 36)
    private UUID userId;

    @Column(name = "board_game_id", nullable = false, length = 36)
    private UUID boardgameId;
}
