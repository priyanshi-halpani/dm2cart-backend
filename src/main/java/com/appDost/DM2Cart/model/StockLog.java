//package com.appDost.DM2Cart.model;
//
//import jakarta.persistence.*;
//
//import java.time.Instant;
//import java.util.UUID;
//
//@Entity
//@Table(name = "stock_logs")
//public class StockLog {
//
//    @Id
//    @GeneratedValue
//    private UUID id;
//
//    @ManyToOne
//    private Inventory inventory;
//
//    private int changeQty;
//    private String reason;
//    private UUID refId;
//
//    private Instant createdAt = Instant.now();
//}
//
