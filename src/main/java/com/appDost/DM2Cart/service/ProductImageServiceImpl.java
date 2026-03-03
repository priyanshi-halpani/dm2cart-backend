//package com.appDost.DM2Cart.service;
//
//import com.appDost.DM2Cart.config.TenantResolver;
//import com.appDost.DM2Cart.model.Product;
//import com.appDost.DM2Cart.model.ProductImage;
//import com.appDost.DM2Cart.model.Tenant;
//import com.appDost.DM2Cart.repository.ProductImageRepository;
//import com.appDost.DM2Cart.repository.ProductRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.UUID;
//
//@Service
//public class ProductImageServiceImpl implements ProductImageService {
//
//    private final ProductRepository productRepository;
//    private final ProductImageRepository imageRepository;
//    private final FileStorageService fileStorageService;
//    private final TenantResolver tenantResolver;
//
//    public ProductImageServiceImpl(ProductRepository productRepository,
//                                   ProductImageRepository imageRepository,
//                                   FileStorageService fileStorageService,
//                                   TenantResolver tenantResolver) {
//        this.productRepository = productRepository;
//        this.imageRepository = imageRepository;
//        this.fileStorageService = fileStorageService;
//        this.tenantResolver = tenantResolver;
//    }
//
//    @Override
//    public void upload(UUID productId, MultipartFile file) {
//
//        // 🔐 Fetch product entity (tenant-safe)
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("Product not found"));
//
//        Tenant currentTenant = tenantResolver.getCurrentTenant();
//
//        if (!product.getTenant().getId().equals(currentTenant.getId())) {
//            throw new RuntimeException("Access denied");
//        }
//
//        // Upload image
//        String imageUrl = fileStorageService.upload(file);
//
//        // Save image
//        ProductImage image = new ProductImage();
//        image.setProduct(product);
//        image.setUrl(imageUrl);
//        image.setPrimary(false);
//
//        imageRepository.save(image);
//    }
//}
