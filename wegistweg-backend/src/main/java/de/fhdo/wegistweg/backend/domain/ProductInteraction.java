package de.fhdo.wegistweg.backend.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
public class ProductInteraction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne User user;

    @NotNull
    private LocalDateTime timestamp;
    @NotNull
    private ProductInteractionType interactionType;

    public ProductInteraction() {
    }

    public ProductInteraction(Product product, User user, LocalDateTime timestamp, ProductInteractionType interactionType) {
        this.product = product;
        this.user = user;
        this.timestamp = timestamp;
        this.interactionType = interactionType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public ProductInteractionType getInteractionType() {
        return interactionType;
    }

    public void setInteractionType(ProductInteractionType interactionType) {
        this.interactionType = interactionType;
    }
}
