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

@Getter
@Setter
@NoArgsConstructor
public class BoardgameModel {
    private UUID id;

    private String name;

    private String description;

    private int releaseYear;


    private String playerNumber;


    private int playTime;
}
