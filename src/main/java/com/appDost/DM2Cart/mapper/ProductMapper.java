package com.appDost.DM2Cart.mapper;

import com.appDost.DM2Cart.dto.ProductImageResponseDTO;
import com.appDost.DM2Cart.dto.ProductRequestDTO;
import com.appDost.DM2Cart.dto.ProductResponseDTO;
import com.appDost.DM2Cart.model.Category;
import com.appDost.DM2Cart.model.Product;
import com.appDost.DM2Cart.model.ProductImage;
import org.springframework.stereotype.Component;


import java.util.Collections;
import java.util.List;

@Component
public class ProductMapper {

    // ===== DTO → ENTITY =====
    public Product toEntity(ProductRequestDTO dto) {
        if (dto == null) return null;

        Product product = new Product();
        product.setTitle(dto.getTitle());
        product.setSku(dto.getSku());
        product.setShortDesc(dto.getShortDesc());
        product.setLongDesc(dto.getLongDesc());
        product.setPrice(dto.getPrice());
        product.setTaxPercent(dto.getTaxPercent());
        product.setActive(dto.isActive());
        product.setAttributes(dto.getAttributes());

        return product;
    }

    // ===== ENTITY → DTO =====
    public ProductResponseDTO toDto(Product product) {
        if (product == null) return null;

        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setId(product.getId());
        dto.setTitle(product.getTitle());
        dto.setSku(product.getSku());
        dto.setPrice(product.getPrice());
        dto.setCurrency(product.getCurrency());
        dto.setTaxPercent(product.getTaxPercent());
        dto.setActive(product.isActive());
        dto.setAttributes(product.getAttributes());
        dto.setCreatedAt(product.getCreatedAt());

        // Images (safe)
        List<ProductImage> images =
                product.getImages() != null ? product.getImages() : Collections.emptyList();

        dto.setImages(
                product.getImages() == null ? List.of() :
                product.getImages().stream().map(img -> {
                    ProductImageResponseDTO i = new ProductImageResponseDTO();
                    i.setId(img.getId());
                    i.setUrl(img.getUrl());
                    i.setAltText(img.getAltText());
                    i.setPosition(img.getPosition());
                    i.setPrimary(img.isPrimary());
                    return i;
                }).toList()

        );

        // Categories (safe)
        List<Category> categories =
                product.getCategories() != null ? (List<Category>) product.getCategories() : Collections.emptyList();

        dto.setCategories(
                product.getCategories() == null ? List.of() :
                        product.getCategories().stream()
                                .map(Category::getName)
                                .toList()
        );


        return dto;
    }

    private ProductImageResponseDTO mapImage(ProductImage img) {
        ProductImageResponseDTO dto = new ProductImageResponseDTO();
        dto.setId(img.getId());
        dto.setUrl(img.getUrl());
        dto.setAltText(img.getAltText());
        dto.setPosition(img.getPosition());
        dto.setPrimary(img.isPrimary());
        return dto;
    }
}
