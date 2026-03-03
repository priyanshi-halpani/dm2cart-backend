//package com.appDost.DM2Cart.model;
//
//import jakarta.persistence.*;
//
//import java.math.BigDecimal;
//import java.time.Instant;
//import java.util.UUID;
//
//@Entity
//@Table(name = "coupons")
//public class Coupon {
//
//    @Id
//    @GeneratedValue
//    private UUID id;
//
//    private String code;
//    private BigDecimal value;
//    private String type;
//
//    @ManyToOne
//    private Tenant tenant;
//
//    private Instant expiry;
//}
