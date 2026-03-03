//package com.appDost.DM2Cart.model;
//
//
//
//import java.io.Serializable;
//import java.util.Objects;
//import java.util.UUID;
//
//public class CartItemId implements Serializable {
//
//    private UUID cartId;
//    private UUID productId;
//
//    // REQUIRED: no-arg constructor
//    public CartItemId() {
//    }
//
//    public CartItemId(UUID cartId, UUID productId) {
//        this.cartId = cartId;
//        this.productId = productId;
//    }
//
//    // REQUIRED: equals & hashCode
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof CartItemId)) return false;
//        CartItemId that = (CartItemId) o;
//        return Objects.equals(cartId, that.cartId)
//                && Objects.equals(productId, that.productId);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(cartId, productId);
//    }
//}
