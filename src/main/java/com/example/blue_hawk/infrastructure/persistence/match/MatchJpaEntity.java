package com.example.blue_hawk.infrastructure.persistence.match;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class MatchJpaEntity {

    @Id
    @JdbcTypeCode(Types.CHAR)
    @Column(name = "id", nullable = false, updatable = false, length = 36)
    private UUID id;

    @JdbcTypeCode(Types.CHAR)
    @Column(name = "userboardgame_id", nullable = false, length = 36)
    private UUID userBoardgameId;

    @Column(name = "max_users", nullable = false)
    private Integer maxUsers;
}
