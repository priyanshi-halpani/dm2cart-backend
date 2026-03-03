//package com.appDost.DM2Cart.model;
//
//
//import jakarta.persistence.*;
//
//import java.math.BigDecimal;
//import java.time.Instant;
//import java.util.UUID;
//
//@Entity
//@Table(name = "orders")
//public class Order {
//
//    @Id
//    @GeneratedValue
//    private UUID id;
//
//    @Column(unique = true)
//    private String orderNumber;
//
//    @ManyToOne
//    private AppUser user;
//
//    @ManyToOne
//    private Tenant tenant;
//
//    private String status;
//    private BigDecimal totalAmount;
//
//    private Instant createdAt = Instant.now();
//}
//
//
//
