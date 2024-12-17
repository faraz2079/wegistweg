package de.fhdo.wegistweg.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class ProductViewCountDto {
    @NotNull
    private ProductDto product;
    @PositiveOrZero
    private long viewCount;

    public ProductViewCountDto() {
    }

    public ProductViewCountDto(ProductDto product, long viewCount) {
        this.product = product;
        this.viewCount = viewCount;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public long getViewCount() {
        return viewCount;
    }

    public void setViewCount(long viewCount) {
        this.viewCount = viewCount;
    }

    @Override
    public String toString() {
        return "ProductViewCountDto{" +
                "product=" + product +
                ", viewCount=" + viewCount +
                '}';
    }
}
