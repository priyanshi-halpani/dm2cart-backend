package com.appDost.DM2Cart.service;

import com.appDost.DM2Cart.dto.JwtResponse;
import com.appDost.DM2Cart.dto.LoginRequest;
import com.appDost.DM2Cart.dto.OtpVerifyRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    JwtResponse login(LoginRequest req);

    JwtResponse verifyOtp(OtpVerifyRequest req);

    ResponseEntity<?> loginSellerOrAdmin(LoginRequest req);

    ResponseEntity<?> loginCustomer(String tenantCode, LoginRequest req);

    JwtResponse verifyCustomerOtp(String tenantCode, OtpVerifyRequest req);
}

