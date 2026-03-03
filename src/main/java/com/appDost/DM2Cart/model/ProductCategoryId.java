//package com.appDost.DM2Cart.model;
//
//import java.io.Serializable;
//import java.util.Objects;
//import java.util.UUID;
//
//public class ProductCategoryId implements Serializable {
//
//    private UUID product;
//    private UUID category;
//
//    // ===== Constructors =====
//    public ProductCategoryId() {}
//
//    public ProductCategoryId(UUID product, UUID category) {
//        this.product = product;
//        this.category = category;
//    }
//
//    // ===== Getters & Setters =====
//    public UUID getProduct() {
//        return product;
//    }
//
//    public void setProduct(UUID product) {
//        this.product = product;
//    }
//
//    public UUID getCategory() {
//        return category;
//    }
//
//    public void setCategory(UUID category) {
//        this.category = category;
//    }
//
//    // ===== equals & hashCode (required for composite key) =====
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof ProductCategoryId)) return false;
//        ProductCategoryId that = (ProductCategoryId) o;
//        return Objects.equals(getProduct(), that.getProduct()) &&
//                Objects.equals(getCategory(), that.getCategory());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getProduct(), getCategory());
//    }
//}
