//package com.appDost.DM2Cart.model;
//
//import jakarta.persistence.*;
//
//import java.math.BigDecimal;
//import java.time.Instant;
//import java.util.UUID;
//
//@Entity
//@Table(name = "payments")
//public class Payment {
//
//    @Id
//    @GeneratedValue
//    private UUID id;
//
//    @OneToOne
//    private Order order;
//
//    private String provider;
//    private String status; // pending, success, failed
//    private BigDecimal amount;
//
//    private Instant createdAt = Instant.now();
//}
//
