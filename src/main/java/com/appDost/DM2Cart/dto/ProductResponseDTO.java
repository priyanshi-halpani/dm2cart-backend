package com.appDost.DM2Cart.dto;
import java.time.Instant;
import java.util.List;
import java.util.Map;





public class ProductResponseDTO {

    private Long id;
    private String title;
    private String sku;
    private Double price;
    private String currency;
    private Double taxPercent;
    private boolean isActive;

    private Map<String, Object> attributes;
//    private List<String> imageUrls;
    private List<String> categories;
    private Instant createdAt;
    private List<ProductImageResponseDTO> images;


    // ===== Getters & Setters =====


    public List<ProductImageResponseDTO> getImages() {
        return images;
    }

    public void setImages(List<ProductImageResponseDTO> images) {
        this.images = images;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getTaxPercent() {
        return taxPercent;
    }

    public void setTaxPercent(Double taxPercent) {
        this.taxPercent = taxPercent;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }


    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}

