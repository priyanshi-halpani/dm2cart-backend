package com.appDost.DM2Cart.service;

import com.appDost.DM2Cart.dto.BankDTO;
import com.appDost.DM2Cart.dto.BasicInfoDTO;
import com.appDost.DM2Cart.dto.KycDTO;
import com.appDost.DM2Cart.dto.StoreSettingsDTO;
import com.appDost.DM2Cart.model.Seller;

public interface SellerService {

    Seller updateBasicInfo(Long sellerId, BasicInfoDTO dto);

    Seller updateKyc(Long sellerId, KycDTO dto);

    Seller updateBank(Long sellerId, BankDTO dto);

    Seller updateStoreSettings(Long sellerId, StoreSettingsDTO dto);

//    Seller getPerformance(Long sellerId);

    Seller getSeller();
}
