//package com.appDost.DM2Cart.model;
//
//import jakarta.persistence.*;
//
//import java.time.Instant;
//import java.util.UUID;
//
//@Entity
//@Table(name = "inventory")
//public class Inventory {
//
//    @Id
//    @GeneratedValue
//    private UUID id;
//
//    @OneToOne(optional = false)
//    private Product product;
//
//    @ManyToOne(optional = false)
//    private Tenant tenant;
//
//    private int quantity;
//    private int reserved;
//    private int reorderLevel;
//
//    private Instant updatedAt = Instant.now();
//}
