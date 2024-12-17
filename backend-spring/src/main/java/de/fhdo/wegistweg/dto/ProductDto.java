package de.fhdo.wegistweg.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class ProductDto {
    @NotNull
    private Long id;

    @NotNull
    @PositiveOrZero
    private Integer stock;
    @NotNull
    @PositiveOrZero
    private Double price;

    public ProductDto() {
    }

    public ProductDto(Long id, Integer stock, Double price) {
        this.id = id;
        this.stock = stock;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "id=" + id +
                ", stock=" + stock +
                ", price=" + price +
                '}';
    }
}
