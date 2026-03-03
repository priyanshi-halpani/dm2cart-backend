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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/auth")
public class SellerSignupController {

    @Autowired
    private UserRepository  userRepo;



    @Autowired
    private PasswordEncoder  passwordEncoder;

    @Autowired
    private SellerRepository sellerRepository;

    @PostMapping ("/seller/signup")
    public ResponseEntity<?> sellerSignup(@Valid  @RequestBody SignupRequest  req) {

        if (userRepo.findByEmail(req.getEmail()) != null) {
            return new ResponseEntity<>("Email already exists", HttpStatus.BAD_REQUEST);
        }

        if (userRepo.findByMobile(req.getMobile()) != null) {
            return new ResponseEntity<>("Mobile already exists", HttpStatus.BAD_REQUEST);
        }



        if(userRepo.findByUsername(req.getUsername())!= null)
            return new ResponseEntity<>("username  already exist.", HttpStatus.BAD_REQUEST);



        Seller seller = new Seller();
        seller.setBusinessName(req.getUsername() + "_shop");//store mob and email
        seller.setEmail(req.getEmail());
        seller.setMobile(req.getMobile());
        sellerRepository.save(seller);


        // Create Seller
        AppUser  user_seller = new AppUser();
        user_seller.setUsername(req.getUsername());
        user_seller.setEmail(req.getEmail());
        user_seller.setMobile(req.getMobile());
        user_seller.setPassword(passwordEncoder.encode(req.getPassword()));
        user_seller.setRole(Roles.SELLER);
        user_seller.setSeller(seller);

        userRepo.save(user_seller);

        return new ResponseEntity<>("Seller registered. Tenant: " + seller.getBusinessName() ,HttpStatus.OK);

    }
}
