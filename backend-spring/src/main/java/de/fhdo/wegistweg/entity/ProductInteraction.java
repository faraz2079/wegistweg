package de.fhdo.wegistweg.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
public abstract class ProductInteraction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Product product;

    @NotNull
    private LocalDateTime timestamp;
    @NotNull
    private ProductInteractionType interactionType;

    public ProductInteraction() {
    }

    public ProductInteraction(Product product, LocalDateTime timestamp, ProductInteractionType interactionType) {
        this.product = product;
        this.timestamp = timestamp;
        this.interactionType = interactionType;
    }

    /**
     * @return the identifier of the actor that is interacting with the product
     */
    public abstract String getActorIdentifier();

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
