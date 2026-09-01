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
@Table(name = "Publisher")
@Getter
@Setter
@NoArgsConstructor
public class PublisherModel {
    @Id
    @Column(name = "uuid", nullable = false, updatable = false, length = 36)
    private UUID uuid;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "country", nullable = false, length = 150)
    private String country;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;
}
