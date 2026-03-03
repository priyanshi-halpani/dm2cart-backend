package com.appDost.DM2Cart.controller;

import com.appDost.DM2Cart.config.CustomUserDetails;
import com.appDost.DM2Cart.dto.BankDTO;
import com.appDost.DM2Cart.dto.BasicInfoDTO;
import com.appDost.DM2Cart.dto.KycDTO;
import com.appDost.DM2Cart.dto.StoreSettingsDTO;
import com.appDost.DM2Cart.model.Seller;
import com.appDost.DM2Cart.service.CustomUserDetailsService;
import com.appDost.DM2Cart.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller/profile")
public class SellerProfileController {

    @Autowired
    private CustomUserDetailsService customUserDetails;

    @Autowired
    private SellerService sellerService;

    // ---------- BASIC INFO ----------

    @PutMapping("/basic")
    public Seller updateBasic(
            @AuthenticationPrincipal CustomUserDetails user,
            @RequestBody BasicInfoDTO dto) {

        Long sellerId = user.getAppUser().getSeller().getId();
        return sellerService.updateBasicInfo(sellerId, dto);
    }


    // ---------- KYC ----------
    @PutMapping("/kyc")
    public Seller updateKyc(@RequestBody KycDTO dto) {
        Seller seller=sellerService.getSeller();
        return sellerService.updateKyc(seller.getId(), dto);
    }

    // ---------- BANK ----------
    @PutMapping("/bank")
    public Seller updateBank(@RequestBody BankDTO dto) {
        Seller seller=sellerService.getSeller();
        return sellerService.updateBank(seller.getId(), dto);
    }

    // ---------- STORE SETTINGS ----------
    @PutMapping("/store-settings")
    public Seller updateStoreSettings(@RequestBody StoreSettingsDTO dto) {
        Seller seller=sellerService.getSeller();
        return sellerService.updateStoreSettings(seller.getId(), dto);
    }


    // ---------- GET FULL PROFILE ----------
    @GetMapping("/")
    public Seller getSeller() {
        return sellerService.getSeller();
    }
}

