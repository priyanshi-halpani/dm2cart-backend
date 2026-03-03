//package com.appDost.DM2Cart.model;
//
//import jakarta.persistence.*;
//
//
//import java.util.UUID;
//
//
//@Entity
//@Table(name = "reviews")
//public class Review {
//
//    @Id
//    @GeneratedValue
//    private UUID id;
//
//    @ManyToOne
//    private Product product;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    private AppUser user;
//
//    private int rating;
//    private String comment;
//}
