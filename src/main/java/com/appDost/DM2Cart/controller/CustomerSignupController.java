package com.appDost.DM2Cart.controller;

import com.appDost.DM2Cart.dto.SignupRequest;
import com.appDost.DM2Cart.model.AppUser;
import com.appDost.DM2Cart.model.Roles;
import com.appDost.DM2Cart.model.Seller;
import com.appDost.DM2Cart.repository.SellerRepository;
import com.appDost.DM2Cart.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sellers/{businessName}/auth")
public class CustomerSignupController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> customerSignup(
            @PathVariable String businessName,
            @Valid @RequestBody SignupRequest req
    ) {

        // ✅ Find Seller instead of Tenant
        Seller seller = sellerRepository.findByBusinessName(businessName)
                .orElseThrow(() -> new RuntimeException("Seller not found"));

        // ✅ Check duplicate email within THIS seller
        if (userRepo.findByEmailAndSeller_Id(req.getEmail(), seller.getId()).isPresent()) {
            return new ResponseEntity<>("Email already exists in this shop", HttpStatus.BAD_REQUEST);
        }

        // ✅ Check duplicate mobile within THIS seller
        if (userRepo.findByMobileAndSeller_Id(req.getMobile(), seller.getId()).isPresent()) {
            return new ResponseEntity<>("Mobile number already registered", HttpStatus.BAD_REQUEST);
        }

        // ✅ Create Customer (linked to Seller)
        AppUser customer = new AppUser();
        customer.setUsername(req.getUsername());
        customer.setEmail(req.getEmail());
        customer.setMobile(req.getMobile());
        customer.setPassword(passwordEncoder.encode(req.getPassword()));
        customer.setRole(Roles.CUSTOMER);
        customer.setSeller(seller);   // 🔥 IMPORTANT CHANGE

        userRepo.save(customer);

        return new ResponseEntity<>(
                "Customer registered under seller: " + businessName,
                HttpStatus.OK
        );
    }
}

