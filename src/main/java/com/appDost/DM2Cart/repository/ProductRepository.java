package com.appDost.DM2Cart.repository;

import com.appDost.DM2Cart.model.Product;
import com.appDost.DM2Cart.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findBySeller(Seller seller);

    @Query("""
    SELECT p FROM Product p 
    LEFT JOIN FETCH p.categories 
    LEFT JOIN FETCH p.images 
    WHERE p.id = :id
    """)
    Optional<Product> findByIdWithRelations(@Param("id") Long id);

    @Query("""
    SELECT DISTINCT p FROM Product p
    LEFT JOIN FETCH p.categories
    LEFT JOIN FETCH p.images
    WHERE p.seller = :seller
    """)
    List<Product> findAllBySellerWithRelations(@Param("seller") Seller seller);


    boolean existsBySku(String sku);
}
