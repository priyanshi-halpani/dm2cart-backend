//package com.appDost.DM2Cart.service;
//
//import com.appDost.DM2Cart.model.Inventory;
//import com.appDost.DM2Cart.model.Product;
//import com.appDost.DM2Cart.model.TenantContext;
//import com.appDost.DM2Cart.repository.InventoryRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.UUID;
//
//@Service
//public class InventoryService {
//
//    @Autowired
//    private InventoryRepository inventoryRepo;
//
//    @Autowired
//    private StockLogRepository stockLogRepo;
//
//    public Inventory create(Product product, int qty) {
//
//        Inventory inv = new Inventory();
//        inv.setProduct(product);
//        inv.setTenant(TenantContext.getTenant());
//        inv.setQuantity(qty);
//        inv.setReserved(0);
//
//        return inventoryRepo.save(inv);
//    }
//
//    public void reserveStock(UUID productId, int qty, UUID orderId) {
//
//        Inventory inv = inventoryRepo
//                .findByProduct_IdAndTenant_Id(productId, TenantContext.getTenant())
//                .orElseThrow(() -> new RuntimeException("Inventory not found"));
//
//        if (inv.getQuantity() - inv.getReserved() < qty)
//            throw new RuntimeException("Out of stock");
//
//        inv.setReserved(inv.getReserved() + qty);
//        inventoryRepo.save(inv);
//
//        log(inv, -qty, "RESERVE", orderId);
//    }
//
//    public void confirmStock(UUID productId, int qty, UUID orderId) {
//
//        Inventory inv = inventoryRepo
//                .findByProduct_IdAndTenant_Id(productId, TenantContext.getTenant())
//                .orElseThrow();
//
//        inv.setReserved(inv.getReserved() - qty);
//        inv.setQuantity(inv.getQuantity() - qty);
//        inventoryRepo.save(inv);
//
//        log(inv, -qty, "SALE", orderId);
//    }
//
//    private void log(Inventory inv, int change, String reason, UUID refId) {
//        StockLog log = new StockLog();
//        log.setInventory(inv);
//        log.setChangeQty(change);
//        log.setReason(reason);
//        log.setRefId(refId);
//        stockLogRepo.save(log);
//    }
//}
//
