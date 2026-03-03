//package com.appDost.DM2Cart.repository;
//
//import com.appDost.DM2Cart.model.Inventory;
//import org.springframework.data.jpa.repository.JpaRepository;
//
//import java.util.Optional;
//import java.util.UUID;
//
//public interface InventoryRepository extends JpaRepository<Inventory, UUID> {
//
//    Optional<Inventory> findByProduct_IdAndTenant_Id(UUID productId, UUID tenantId);
//}
