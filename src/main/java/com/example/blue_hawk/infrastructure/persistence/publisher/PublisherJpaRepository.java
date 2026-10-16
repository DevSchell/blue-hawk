package com.example.blue_hawk.infrastructure.persistence.publisher;

import com.example.blue_hawk.domain.entity.publisher.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PublisherJpaRepository extends JpaRepository<PublisherJpaEntity, UUID> {

    @Query("SELECT p FROM PublisherJpaEntity p " +
            "WHERE (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) " +
            "AND (:country IS NULL OR p.country = :country)")
    Page<PublisherJpaEntity> findAllFiltered(
            @Param("name") String name,
            @Param("country") Country country,
            Pageable pageable);
}
