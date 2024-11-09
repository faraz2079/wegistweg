package de.fhdo.wegistweg.backend.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class ProductInteraction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Product product;

    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private ProductInteractionType interactionType;


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

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalDateTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalDateTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalDateTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public ProductInteractionType getInteractionType() {
        return interactionType;
    }

    public void setInteractionType(ProductInteractionType interactionType) {
        this.interactionType = interactionType;
    }
}
