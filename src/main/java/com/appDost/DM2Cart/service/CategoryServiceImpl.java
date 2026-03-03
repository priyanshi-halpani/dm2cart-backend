package com.appDost.DM2Cart.service;

import com.appDost.DM2Cart.dto.CategoryRequestDTO;
import com.appDost.DM2Cart.dto.CategoryResponseDTO;
import com.appDost.DM2Cart.model.Category;
import com.appDost.DM2Cart.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // ================= CREATE =================
    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO request) {

        Category category = new Category();
        category.setName(request.getName());
        category.setSlug(generateSlug(request.getName()));
        category.setMetadata(request.getMetadata());

        // Parent handling
        if (request.getParentId() != null) {
            Category parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
            category.setParent(parent);
        }

        Category saved = categoryRepository.save(category);
        return toResponseDTO(saved);
    }

    // ================= UPDATE =================
    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO request) {

        Category category = categoryRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setName(request.getName());
        category.setMetadata(request.getMetadata());

        // Update parent if provided
        if (request.getParentId() != null) {
            if (request.getParentId().equals(id)) {
                throw new RuntimeException("Category cannot be parent of itself");
            }
            Category parent = categoryRepository.findById(request.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found"));
            category.setParent(parent);
        } else {
            category.setParent(null);
        }

        return toResponseDTO(category);
    }

    // ================= GET BY ID =================
    @Override
    public CategoryResponseDTO getCategoryById(Long id) {

        Category category = categoryRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        return toResponseDTO(category);
    }

    // ================= GET ALL =================
    @Override
    public List<CategoryResponseDTO> getAllCategories() {

        return categoryRepository.findAllByActiveTrue()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // ================= SOFT DELETE =================
    @Override
    public void deleteCategory(Long id) {

        Category category = categoryRepository.findByIdAndActiveTrue(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setActive(false);
    }

    // ================= HELPERS =================

    private String generateSlug(String name) {
        return name
                .toLowerCase()
                .trim()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("(^-|-$)", "");
    }

    private CategoryResponseDTO toResponseDTO(Category category) {

        CategoryResponseDTO dto = new CategoryResponseDTO();
        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setSlug(category.getSlug());
        dto.setActive(category.isActive());
        dto.setCreatedAt(category.getCreatedAt());
        dto.setUpdatedAt(category.getUpdatedAt());

        if (category.getParent() != null) {
            dto.setParentId(category.getParent().getId());
        }

        return dto;
    }
}
