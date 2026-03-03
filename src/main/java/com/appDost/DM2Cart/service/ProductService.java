package com.appDost.DM2Cart.service;

import com.appDost.DM2Cart.dto.ProductRequestDTO;
import com.appDost.DM2Cart.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO createProduct(ProductRequestDTO dto);

    ProductResponseDTO updateProduct(Long productId, ProductRequestDTO dto);

    ProductResponseDTO getProductById(Long productId);

    List<ProductResponseDTO> getProductsBySeller();

    void deleteProduct(Long productId);
}
