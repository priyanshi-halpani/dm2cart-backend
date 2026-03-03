package com.appDost.DM2Cart.controller;

import com.appDost.DM2Cart.dto.JwtResponse;
import com.appDost.DM2Cart.dto.LoginRequest;
import com.appDost.DM2Cart.dto.OtpVerifyRequest;
import com.appDost.DM2Cart.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        return authService.loginSellerOrAdmin(req);
    }
}

