package com.appDost.DM2Cart.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface ProductImageService {
    void upload(Long productId, MultipartFile file);

}

