package com.appDost.DM2Cart.controller;

import com.appDost.DM2Cart.dto.ProductRequestDTO;
import com.appDost.DM2Cart.dto.ProductResponseDTO;
import com.appDost.DM2Cart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductResponseDTO create(@RequestBody ProductRequestDTO dto) {
        return productService.createProduct(dto);
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDTO update(@PathVariable Long id, @RequestBody ProductRequestDTO dto) {
        return productService.updateProduct(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @GetMapping
    public List<ProductResponseDTO> get() {
        return productService.getProductsBySeller();
    }

}
