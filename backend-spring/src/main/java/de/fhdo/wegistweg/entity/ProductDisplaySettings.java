package de.fhdo.wegistweg.entity;

import jakarta.persistence.*;

@Entity
public class ProductDisplaySettings {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Product product;

    private boolean displayStockLevel;
    private boolean displayViews;


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

    public boolean isDisplayStockLevel() {
        return displayStockLevel;
    }

    public void setDisplayStockLevel(boolean displayStockLevel) {
        this.displayStockLevel = displayStockLevel;
    }

    public boolean isDisplayViews() {
        return displayViews;
    }

    public void setDisplayViews(boolean displayViews) {
        this.displayViews = displayViews;
    }
}
