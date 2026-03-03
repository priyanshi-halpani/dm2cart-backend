package com.appDost.DM2Cart.controller;

import com.appDost.DM2Cart.dto.JwtResponse;
import com.appDost.DM2Cart.dto.LoginRequest;
import com.appDost.DM2Cart.dto.OtpVerifyRequest;
import com.appDost.DM2Cart.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tenants/{tenantCode}/auth")
public class CustomerAuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @PathVariable String tenantCode,
            @RequestBody LoginRequest req
    ) {
        System.out.println("req is:"+req);
        return authService.loginCustomer(tenantCode, req);
    }

    @PostMapping("/verify-otp")
    public JwtResponse verifyOtp(
            @PathVariable String tenantCode,
            @RequestBody OtpVerifyRequest req
    ) {
        return authService.verifyCustomerOtp(tenantCode, req);
    }
}

