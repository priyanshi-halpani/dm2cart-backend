//package com.appDost.DM2Cart.service;
//
//import com.appDost.DM2Cart.model.AppUser;
//import com.appDost.DM2Cart.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.*;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//
//@Service
//public class CustomUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepo;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        AppUser u = userRepo.findByUsername(username);
//        if (u == null) {
//            // try mobile as username fallback
//            u = userRepo.findByMobile(username);
//        }
//        if (u == null) {
//            throw new UsernameNotFoundException("User not found: " + username);
//        }
//
//        // grant ROLE_ based on enum
//        String roleName = "ROLE_" + u.getRole().name();
//
//        return org.springframework.security.core.userdetails.User
//                .withUsername(u.getUsername())
//                .password(u.getPassword())
//                .authorities(roleName)
//                .accountExpired(false)
//                .accountLocked(false)
//                .credentialsExpired(false)
//                .disabled(false)
//                .build();
//    }
//}
package com.appDost.DM2Cart.service;

import com.appDost.DM2Cart.config.CustomUserDetails;
import com.appDost.DM2Cart.model.AppUser;
import com.appDost.DM2Cart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        // First try username
        AppUser u = userRepo.findByUsername(username);

        // If not found, try mobile (your real login field)
        if (u == null) {
            u = userRepo.findByMobile(username);
        }

        if (u == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }

        return new CustomUserDetails(u);   // 🔥 IMPORTANT CHANGE
    }
}
