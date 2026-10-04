package com.example.blue_hawk.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Offer {

    private UUID id;
    private UUID userBoardgameId;
    private BigDecimal price;
    private OfferStatus status;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Offer(String userBoardgameId, BigDecimal price, OfferStatus status, String description) {
        this.id = UUID.randomUUID();
        this.userBoardgameId = UUID.fromString(userBoardgameId);
        this.price = price;
        this.status = status != null ? status : OfferStatus.ACTIVE;
        this.description = description;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Offer(UUID id, UUID userBoardgameId, BigDecimal price, OfferStatus status,
                 String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userBoardgameId = userBoardgameId;
        this.price = price;
        this.status = status;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getUserBoardgameId() { return userBoardgameId; }
    public void setUserBoardgameId(UUID userBoardgameId) { this.userBoardgameId = userBoardgameId; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public OfferStatus getStatus() { return status; }
    public void setStatus(OfferStatus status) { this.status = status; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
