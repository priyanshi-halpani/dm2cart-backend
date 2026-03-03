package com.appDost.DM2Cart.repository;

import com.appDost.DM2Cart.model.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
