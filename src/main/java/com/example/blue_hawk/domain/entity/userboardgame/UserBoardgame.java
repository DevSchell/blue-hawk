package com.example.blue_hawk.domain.entity.userboardgame;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "UserBoardGame")
public class UserBoardgame {

    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", nullable = false, updatable = false, length = 36)
    private UUID id;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "User_id", nullable = false, length = 36)
    private UUID userId;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "BoardGame_id", nullable = false, length = 36)
    private UUID boardgameId;

    protected UserBoardgame() {
        // JPA exigência do Hibernate
    }

    public UserBoardgame(String userId, String boardgameId) {
        this.userId = UUID.fromString(userId);
        this.boardgameId = UUID.fromString(boardgameId);
        this.id = generateUUID();
    }

    public UserBoardgame(UUID id, UUID userId, UUID boardgameId) {
        this.id = id;
        this.userId = userId;
        this.boardgameId = boardgameId;
    }

    private UUID generateUUID() {
        return UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID _userId) {
        this.userId = _userId;
    }

    public UUID getBoardgameId() {
        return boardgameId;
    }

    public void setBoardgameId(UUID _boardgameId) {
        this.boardgameId = _boardgameId;
    }
}
