package com.appDost.DM2Cart.repository;

import com.appDost.DM2Cart.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsBySlug(String slug);

    Optional<Category> findByIdAndActiveTrue(Long id);

    List<Category> findAllByActiveTrue();

    List<Category> findByParentIsNullAndActiveTrue();
}
