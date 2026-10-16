package com.example.blue_hawk.infrastructure.persistence.boardgame;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Convert;
import jakarta.persistence.Converter;
import jakarta.persistence.AttributeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.UUID;
import java.time.LocalDate;
import java.sql.Date;

@Entity
@Table(name = "board_game")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardgameJpaEntity {
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

    @Convert(converter = YearDateConverter.class)
    @Column(name = "release_date")
    private int releaseYear;

    @Convert(converter = StringIntConverter.class)
    @Column(name = "player_number")
    private String playerNumber;

    @JdbcTypeCode(Types.INTEGER)
    @Column(name = "gameplay_time")
    private int playTime;

    @Converter
    public static class YearDateConverter implements AttributeConverter<Integer, Date> {
        @Override
        public Date convertToDatabaseColumn(Integer year) {
            if (year == null || year == 0) return Date.valueOf(LocalDate.now());
            return Date.valueOf(LocalDate.of(year, 1, 1));
        }

        @Override
        public Integer convertToEntityAttribute(Date dbData) {
            if (dbData == null) return null;
            return dbData.toLocalDate().getYear();
        }
    }

    @Converter
    public static class StringIntConverter implements AttributeConverter<String, Integer> {
        @Override
        public Integer convertToDatabaseColumn(String attribute) {
            if (attribute == null) return null;
            try { return Integer.parseInt(attribute); } catch (NumberFormatException e) { return 0; }
        }

        @Override
        public String convertToEntityAttribute(Integer dbData) {
            return dbData == null ? null : String.valueOf(dbData);
        }
    }
}
