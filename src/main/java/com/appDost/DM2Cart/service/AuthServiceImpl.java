//package com.appDost.DM2Cart.service;
//
//import com.appDost.DM2Cart.config.JwtUtil;
//import com.appDost.DM2Cart.dto.OtpVerifyRequest;
//import com.appDost.DM2Cart.model.AppUser;
//import com.appDost.DM2Cart.model.Roles;
//import com.appDost.DM2Cart.model.Tenant;
//import com.appDost.DM2Cart.repository.TenantRepository;
//import com.appDost.DM2Cart.repository.UserRepository;
//import com.appDost.DM2Cart.dto.JwtResponse;
//import com.appDost.DM2Cart.dto.LoginRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import javax.management.relation.Role;
//import java.util.Random;
//
//@Service
//public class AuthServiceImpl implements AuthService {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Autowired
//    private TenantRepository tenantRepo;
//
//    @Autowired
//    private UserRepository userRepo;
//
////    public JwtResponse login(LoginRequest req) {
////
////        AppUser user = userRepo.findByMobile(req.getMobile());
////
////        if (user == null) {
////            return JwtResponse.failure("Invalid Mobile Number!");
////        }
////
////        // --- USER → OTP Login ---
////        if (user.getRole() == Roles.CUSTOMER) {
////
////            String otp = String.valueOf(new Random().nextInt(900000) + 100000);
////
////            user.setOtp(otp);
////            userRepo.save(user);
////
////            return JwtResponse.otpSent(req.getMobile());
////        }
////
////        // --- SELLER / ADMIN → Password Login ---
////        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
////            return JwtResponse.failure("Invalid Password!");
////        }
////
////
////        return createJwt(user);
////    }
//public JwtResponse login(LoginRequest req) {
//    AppUser user = userRepo.findByMobile(req.getMobile());
//
//    if (user == null) {
//        return JwtResponse.failure("Invalid Mobile Number!");
//    }
//
//    if (user.getRole() == Roles.CUSTOMER) {
//        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
//        user.setOtp(otp);
//        userRepo.save(user);
//        return JwtResponse.otpSent(req.getMobile());
//    }
//
//    if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
//        return JwtResponse.failure("Invalid Password!");
//    }
//
//    return createJwt(user);
//}
//
//    private JwtResponse createJwt(AppUser user) {
//        String token = jwtUtil.generateToken(user);
//        return JwtResponse.success(token, user.getUsername(), user.getRole().name());
//    }
//
//
//    public JwtResponse verifyOtp(OtpVerifyRequest req) {
//
//        AppUser user = userRepo.findByMobile(req.getMobile());
//
//        if (user == null || user.getRole() != Roles.CUSTOMER) {
//            return JwtResponse.failure("Invalid User!");
//        }
//
//        if (!req.getOtp().equals(user.getOtp())) {
//            return JwtResponse.failure("Invalid OTP!");
//        }
//
//        user.setOtp(null);
//        userRepo.save(user);
//
//        return createJwt(user);
//    }
//
//
//
////    private JwtResponse createJwt(AppUser user) {
////        String token = jwtUtil.generateToken(user);
////
////
////        return JwtResponse.success(
////                token,
////                user.getUsername(),
////                user.getRole().name()
////        );
////    }
//    public JwtResponse loginSellerOrAdmin(LoginRequest req) {
//
//        AppUser user = userRepo.findByMobile(req.getMobile());
//
//        if (user == null) {
//            return JwtResponse.failure("Invalid credentials");
//        }
//
//        if (user.getRole() == Roles.CUSTOMER) {
//            return JwtResponse.failure("Use shop login");
//        }
//
//        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
//            return JwtResponse.failure("Invalid password");
//        }
//
//        return createJwt(user);
//    }
//
//    public JwtResponse loginCustomer(String tenantCode, LoginRequest req) {
//
//        try {
//            Tenant tenant = tenantRepo.findByBusinessName(tenantCode)
//                    .orElseThrow(() -> new RuntimeException("Tenant not found: " + tenantCode));
//
//            AppUser user = userRepo
//                    .findByMobileAndTenant_Id(req.getMobile(), tenant.getId())
//                    .orElseThrow(() -> new RuntimeException("Customer not found in this shop"));
//
//            if (user.getRole() != Roles.CUSTOMER) {
//                return JwtResponse.failure("Not a customer account");
//            }
//
//            String otp = String.valueOf(new Random().nextInt(900000) + 100000);
//            user.setOtp(otp);
//            userRepo.save(user);
//
//            // TODO: send OTP via SMS
//            return JwtResponse.otpSent(req.getMobile());
//
//        } catch (Exception e) {
//            e.printStackTrace(); // 🔴 shows exact issue in console
//            return JwtResponse.failure(e.getMessage());
//        }
//    }
//
//
//    public JwtResponse verifyCustomerOtp(String tenantCode, OtpVerifyRequest req) {
//
//        try {
//            Tenant tenant = tenantRepo.findByBusinessName(tenantCode)
//                    .orElseThrow(() -> new RuntimeException("Tenant not found: " + tenantCode));
//
//            AppUser user = userRepo
//                    .findByMobileAndTenant_Id(req.getMobile(), tenant.getId())
//                    .orElseThrow(() -> new RuntimeException("Customer not found in this shop"));
//
//            if (user.getRole() != Roles.CUSTOMER) {
//                return JwtResponse.failure("Not a customer account");
//            }
//
//            if (user.getOtp() == null) {
//                return JwtResponse.failure("OTP expired or not generated");
//            }
//
//            if (!req.getOtp().equals(user.getOtp())) {
//                return JwtResponse.failure("Invalid OTP");
//            }
//
//            user.setOtp(null);
//            userRepo.save(user);
//
//            return createJwt(user);
//
//        } catch (Exception e) {
//            e.printStackTrace(); // 🔴 VERY IMPORTANT
//            return JwtResponse.failure(e.getMessage());
//        }
//    }
//
//
//
//}

package com.appDost.DM2Cart.service;

import com.appDost.DM2Cart.config.JwtUtil;
import com.appDost.DM2Cart.dto.JwtResponse;
import com.appDost.DM2Cart.dto.LoginRequest;
import com.appDost.DM2Cart.dto.OtpVerifyRequest;
import com.appDost.DM2Cart.model.AppUser;
import com.appDost.DM2Cart.model.Roles;
import com.appDost.DM2Cart.model.Seller;
import com.appDost.DM2Cart.repository.SellerRepository;
import com.appDost.DM2Cart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SellerRepository sellerRepository;   // ✅ NEW

    @Autowired
    private UserRepository userRepo;

    // ======= COMMON LOGIN (SELLER / ADMIN / CUSTOMER) =======
    @Override
    public JwtResponse login(LoginRequest req) {

        AppUser user = userRepo.findByMobile(req.getMobile());

        if (user == null) {
            return JwtResponse.failure("Invalid Mobile Number!");
        }

        // CUSTOMER → OTP login
        if (user.getRole() == Roles.CUSTOMER) {
            String otp = String.valueOf(new Random().nextInt(900000) + 100000);
            user.setOtp(otp);
            userRepo.save(user);
            return JwtResponse.otpSent(req.getMobile());
        }

        // SELLER / ADMIN → Password login
        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            return JwtResponse.failure("Invalid Password!");
        }

        return createJwt(user);
    }

    // ======= VERIFY OTP (CUSTOMER) =======
    @Override
    public JwtResponse verifyOtp(OtpVerifyRequest req) {

        AppUser user = userRepo.findByMobile(req.getMobile());

        if (user == null || user.getRole() != Roles.CUSTOMER) {
            return JwtResponse.failure("Invalid User!");
        }

        if (!req.getOtp().equals(user.getOtp())) {
            return JwtResponse.failure("Invalid OTP!");
        }

        user.setOtp(null);
        userRepo.save(user);

        return createJwt(user);
    }



    private JwtResponse createJwt(AppUser user) {
        String token = jwtUtil.generateToken(user);


        return JwtResponse.success(
                token,
                user.getUsername(),
                user.getRole().name()
        );
    }
    public ResponseEntity<?> loginSellerOrAdmin(LoginRequest req) {


        AppUser user = userRepo.findByMobile(req.getMobile());

        if (user == null) {
            return new ResponseEntity<>(JwtResponse.failure("Invalid credentials"),HttpStatus.BAD_REQUEST);
        }

        if (user.getRole() == Roles.CUSTOMER) {
            return new ResponseEntity<>(JwtResponse.failure("Use shop login"),HttpStatus.BAD_REQUEST);
        }

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            return new ResponseEntity<>(JwtResponse.failure("Invalid password"),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createJwt(user),HttpStatus.OK);
    }


    // ======= CUSTOMER LOGIN UNDER A SELLER (SHOP LOGIN) =======
    @Override
    public ResponseEntity loginCustomer(String businessName, LoginRequest req) {

    //public ResponseEntity<?> loginCustomer(String tenantCode, LoginRequest req) {


        try {
            Seller seller = sellerRepository.findByBusinessName(businessName)
                    .orElseThrow(() ->
                            new RuntimeException("Seller not found: " + businessName));

            AppUser user = userRepo
                    .findByMobileAndSeller_Id(req.getMobile(), seller.getId())
                    .orElseThrow(() ->
                            new RuntimeException("Customer not found in this shop"));

            if (user.getRole() != Roles.CUSTOMER) {
          //      return new ResponseEntity<>(JwtResponse.failure("Not a customer account"), HttpStatus.BAD_REQUEST);
            }

            String otp = String.valueOf(new Random().nextInt(900000) + 100000);
            user.setOtp(otp);
            userRepo.save(user);

            return new ResponseEntity<>(JwtResponse.otpSent(req.getMobile()),HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace(); // 🔴 shows exact issue in console
            return new ResponseEntity<>(JwtResponse.failure(e.getMessage()),HttpStatus.BAD_REQUEST);

        }
    }

    // ======= VERIFY CUSTOMER OTP UNDER SELLER =======
    @Override
    public JwtResponse verifyCustomerOtp(String businessName, OtpVerifyRequest req) {

        try {
            Seller seller = sellerRepository.findByBusinessName(businessName)
                    .orElseThrow(() ->
                            new RuntimeException("Seller not found: " + businessName));

            AppUser user = userRepo
                    .findByMobileAndSeller_Id(req.getMobile(), seller.getId())
                    .orElseThrow(() ->
                            new RuntimeException("Customer not found in this shop"));

            if (user.getRole() != Roles.CUSTOMER) {
                return JwtResponse.failure("Not a customer account");
            }

            if (user.getOtp() == null) {
                return JwtResponse.failure("OTP expired or not generated");
            }

            if (!req.getOtp().equals(user.getOtp())) {
                return JwtResponse.failure("Invalid OTP");
            }

            user.setOtp(null);
            userRepo.save(user);

            return createJwt(user);

        } catch (Exception e) {
            e.printStackTrace();
            return JwtResponse.failure(e.getMessage());
        }
    }

    // ======= JWT CREATION (COMMON) =======
//    private JwtResponse createJwt(AppUser user) {
//        String token = jwtUtil.generateToken(user);
//        return JwtResponse.success(
//                token,
//                user.getUsername(),
//                user.getRole().name()
//        );
//    }
}
