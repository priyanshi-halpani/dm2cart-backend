package com.appDost.DM2Cart.dto;
import java.util.List;
import java.util.Map;
import java.util.*;

public class ProductRequestDTO {

    private String title;
    private String sku;
    private String shortDesc;
    private String longDesc;
    private Double price;
    private Double taxPercent;
    private boolean active = true; // rename from isActive to match entity
    private Map<String, Object> attributes = new HashMap<>();
    private List<Long> categoryIds = new ArrayList<>();



    private List<ProductImageRequestDTO> images = new ArrayList<>();


    // ===== Getters & Setters =====

    public List<ProductImageRequestDTO> getImages() {
        return images;
    }

    public void setImages(List<ProductImageRequestDTO> images) {
        this.images = images;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getShortDesc() { return shortDesc; }
    public void setShortDesc(String shortDesc) { this.shortDesc = shortDesc; }

    public String getLongDesc() { return longDesc; }
    public void setLongDesc(String longDesc) { this.longDesc = longDesc; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Double getTaxPercent() { return taxPercent; }
    public void setTaxPercent(Double taxPercent) { this.taxPercent = taxPercent; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public Map<String, Object> getAttributes() { return attributes; }
    public void setAttributes(Map<String, Object> attributes) { this.attributes = attributes; }

    public List<Long> getCategoryIds() { return categoryIds; }
    public void setCategoryIds(List<Long> categoryIds) { this.categoryIds = categoryIds; }
}


