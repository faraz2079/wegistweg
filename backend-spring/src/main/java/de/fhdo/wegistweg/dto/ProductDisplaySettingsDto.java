package de.fhdo.wegistweg.dto;

public class ProductDisplaySettingsDto {
    private long productId;
    private boolean displayStockLevel;
    private boolean displayViews;

    public ProductDisplaySettingsDto(long productId) {
        this.productId = productId;
        this.displayStockLevel = true;
        this.displayViews = true;
    }

    public ProductDisplaySettingsDto(long productId, boolean displayStockLevel, boolean displayViews) {
        this.productId = productId;
        this.displayStockLevel = displayStockLevel;
        this.displayViews = displayViews;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
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
