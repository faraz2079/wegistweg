package de.fhdo.wegistweg.backend.dto;

import de.fhdo.wegistweg.backend.domain.ProductInteractionType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class ProductInteractionDto {
    @NotNull
    private Long productId;

    // TODO Infer from Auth/Session?
    @NotNull
    private Long userId;
    @NotNull
    private ProductInteractionType interactionType;

    @NotNull
    private LocalDateTime timestamp;

    public  ProductInteractionDto() {}

    public ProductInteractionDto(Long productId, Long userId, ProductInteractionType interactionType, LocalDateTime timestamp) {
        this.productId = productId;
        this.userId = userId;
        this.interactionType = interactionType;
        this.timestamp = timestamp;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public ProductInteractionType getInteractionType() {
        return interactionType;
    }

    public void setInteractionType(ProductInteractionType interactionType) {
        this.interactionType = interactionType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
