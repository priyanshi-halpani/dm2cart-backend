//package com.appDost.DM2Cart.model;
//
//import jakarta.persistence.*;
//
//import java.time.Instant;
//import java.util.UUID;
//
//@Entity
//@Table(name = "carts")
//public class Cart {
//
//    @Id
//    @GeneratedValue
//    private UUID id;
//
//    @OneToOne
//    private AppUser user;
//
//    @ManyToOne
//    private Tenant tenant;
//
//    private Instant createdAt = Instant.now();
//}
//
