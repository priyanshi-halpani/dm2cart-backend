package com.appDost.DM2Cart.repository;

import com.appDost.DM2Cart.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Optional<Seller> findByBusinessName(String businessName);


}
