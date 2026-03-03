package com.appDost.DM2Cart.service;

import com.appDost.DM2Cart.dto.BankDTO;
import com.appDost.DM2Cart.dto.BasicInfoDTO;
import com.appDost.DM2Cart.dto.KycDTO;
import com.appDost.DM2Cart.dto.StoreSettingsDTO;
import com.appDost.DM2Cart.model.AppUser;
import com.appDost.DM2Cart.model.Roles;
import com.appDost.DM2Cart.model.Seller;
import com.appDost.DM2Cart.repository.SellerRepository;
import com.appDost.DM2Cart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepo;

    @Autowired
    private UserRepository userRepository;


    private ProductServiceImpl productServiceImpl;

    private Seller getSellerOrThrow() {


        Seller seller=getLoggedInSeller();
        return seller;
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

    @Override
    public Seller updateBasicInfo(Long sellerId, BasicInfoDTO dto) {
        Seller s = getSellerOrThrow();
        s.setSellerName(dto.sellerName);
        s.setStoreName(dto.storeName);
        s.setMobile(dto.mobile);
        s.setEmail(dto.email);
        s.setPincode(dto.pincode);
        s.setCity(dto.city);
        s.setState(dto.state);
        s.setAbout(dto.about);
        s.setLogoUrl(dto.logoUrl);
        return sellerRepo.save(s);
    }

    @Override
    public Seller updateKyc(Long sellerId, KycDTO dto) {
        Seller s = getSellerOrThrow();
        s.setPanNumber(dto.panNumber);
        s.setGstNumber(dto.gstNumber);
        s.setAadharMasked(dto.aadharMasked);
        s.setBusinessProofUrl(dto.businessProofUrl);
        s.setKycStatus("SUBMITTED");
        return sellerRepo.save(s);
    }

    @Override
    public Seller updateBank(Long sellerId, BankDTO dto) {
        Seller s = getSellerOrThrow();
        s.setAccountHolderName(dto.accountHolderName);
        s.setBankName(dto.bankName);
        s.setIfscCode(dto.ifscCode);
        s.setPaymentMethod(dto.paymentMethod);
        return sellerRepo.save(s);
    }

    @Override
    public Seller updateStoreSettings(Long sellerId, StoreSettingsDTO dto) {
        Seller s = getSellerOrThrow();
        s.setOperatingDaysTime(dto.operatingDaysTime);
        s.setServiceablePincodes(dto.serviceablePincodes);
        s.setReturnRefundPolicy(dto.returnRefundPolicy);
        return sellerRepo.save(s);
    }

//    @Override
//    public Seller getPerformance(Long sellerId) {
//        return getSellerOrThrow(sellerId);
//    }

    @Override
    public Seller getSeller() {
        return getSellerOrThrow();
    }
}
