package com.appDost.DM2Cart.controller;

import com.appDost.DM2Cart.dto.CategoryRequestDTO;
import com.appDost.DM2Cart.dto.CategoryResponseDTO;
import com.appDost.DM2Cart.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        value = "/api/categories",
        produces = "application/json"
)
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // ================= CREATE (ADMIN) =================
    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryResponseDTO> createCategory(
            @RequestBody CategoryRequestDTO request
    ) {
        CategoryResponseDTO response = categoryService.createCategory(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // ================= UPDATE (ADMIN) =================
    @PutMapping(value = "/{id}", consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryResponseDTO> updateCategory(
            @PathVariable Long id,
            @RequestBody CategoryRequestDTO request
    ) {
        CategoryResponseDTO response = categoryService.updateCategory(id, request);
        return ResponseEntity.ok(response);
    }

    // ================= DELETE (ADMIN - SOFT) =================
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    // ================= GET BY ID (PUBLIC) =================
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable Long id) {
        CategoryResponseDTO response = categoryService.getCategoryById(id);
        return ResponseEntity.ok(response);
    }

    // ================= GET ALL (PUBLIC) =================
    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
        List<CategoryResponseDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
}
