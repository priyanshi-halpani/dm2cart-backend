package com.appDost.DM2Cart.service;

import com.appDost.DM2Cart.dto.ProductRequestDTO;
import com.appDost.DM2Cart.dto.ProductResponseDTO;
import com.appDost.DM2Cart.mapper.ProductMapper;
import com.appDost.DM2Cart.model.*;
import com.appDost.DM2Cart.repository.CategoryRepository;
import com.appDost.DM2Cart.repository.ProductRepository;
import com.appDost.DM2Cart.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              ProductMapper productMapper,
                              UserRepository userRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
        this.userRepository = userRepository;
    }


    // ================= CREATE =================
    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO dto) {

//        Tenant tenant = tenantResolver.getCurrentTenant();
//
        Product product = productMapper.toEntity(dto);
//        product.setTenant(tenant);
        Seller seller = getLoggedInSeller();
        product.setSeller(seller);


        if (dto.getCategoryIds() != null && !dto.getCategoryIds().isEmpty()) {
            List<Category> categories =
                    categoryRepository.findAllById(dto.getCategoryIds());
            product.setCategories(new HashSet<>(categories));
        }

        Product saved = productRepository.save(product);

// 🔹 RE-FETCH with relations to avoid LazyInitializationException
        Product reloaded = productRepository.findById(saved.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return productMapper.toDto(reloaded);

    }

    // ================= UPDATE =================
    @Override
    public ProductResponseDTO updateProduct(Long productId, ProductRequestDTO dto) {

        Product existing = getProductEntityById(productId);

        existing.setTitle(dto.getTitle());
        existing.setSku(dto.getSku());
        existing.setShortDesc(dto.getShortDesc());
        existing.setLongDesc(dto.getLongDesc());
        existing.setPrice(dto.getPrice());
        existing.setTaxPercent(dto.getTaxPercent());
        existing.setActive(dto.isActive());
        existing.setAttributes(dto.getAttributes());

        if (dto.getCategoryIds() != null) {
            List<Category> categories =
                    categoryRepository.findAllById(dto.getCategoryIds());
            existing.setCategories(new HashSet<>(categories));
        }

        // ===== IMAGE HANDLING (MOVED FROM ENTITY) =====
        existing.getImages().clear();

        if (dto.getImages() != null) {
            dto.getImages().forEach(imgDto -> {
                ProductImage image = new ProductImage();
                image.setUrl(imgDto.getUrl());
                image.setAltText(imgDto.getAltText());
                image.setPosition(imgDto.getPosition());
                image.setPrimary(imgDto.isPrimary());
                linkImage(existing, image);
            });
        }

        Product updated = productRepository.save(existing);
        return productMapper.toDto(updated);
    }

    // ================= IMAGE HELPER (SERVICE LEVEL) =================
    private void linkImage(Product product, ProductImage image) {
        product.getImages().add(image);
        image.setProduct(product);
    }

    // ================= READ =================
    @Override
    public ProductResponseDTO getProductById(Long productId) {
        return productMapper.toDto(getProductEntityById(productId));
    }

//    @Override
//    public List<ProductResponseDTO> getProductsBySeller() {
//        Tenant tenant = tenantResolver.getCurrentTenant();
//        return productRepository.findByTenant(tenant)
//                .stream()
//                .map(productMapper::toDto)
//                .toList();
//    }
    @Override
    public List<ProductResponseDTO> getProductsBySeller() {

    Seller seller = getLoggedInSeller();

    return productRepository.findAllBySellerWithRelations(seller)
            .stream()
            .map(productMapper::toDto)
            .toList();
    }


    // ================= DELETE =================
    @Override
    public void deleteProduct(Long productId) {
        productRepository.delete(getProductEntityById(productId));
    }

    // ================= TENANT SAFE FETCH =================
    private Product getProductEntityById(Long productId) {

        Product product = productRepository.findByIdWithRelations(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));


//        if (!product.getTenant().getId()
//                .equals(tenantResolver.getCurrentTenant().getId())) {
//            throw new RuntimeException("Access denied");
//        }
        Seller seller = getLoggedInSeller();

        if (!product.getSeller().getId().equals(seller.getId())) {
            throw new RuntimeException("Access denied");
        }

        return product;
    }

    public Seller getLoggedInSeller() {

        String mobile = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();   // because your JWT subject is mobile

        AppUser user = userRepository.findByMobile(mobile);

        if (user == null || user.getRole() != Roles.SELLER) {
            throw new RuntimeException("Not an authenticated seller");
        }

        return user.getSeller();
    }

}

