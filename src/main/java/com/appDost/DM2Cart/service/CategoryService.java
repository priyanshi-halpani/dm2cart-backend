package com.appDost.DM2Cart.service;

import com.appDost.DM2Cart.dto.CategoryRequestDTO;
import com.appDost.DM2Cart.dto.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {

    CategoryResponseDTO createCategory(CategoryRequestDTO request);

    CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO request);

    CategoryResponseDTO getCategoryById(Long id);

    List<CategoryResponseDTO> getAllCategories();

    void deleteCategory(Long id);
}

