package com.appDost.DM2Cart.repository;

import com.appDost.DM2Cart.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByEmail(String email);

    AppUser findByMobile(String mobile);

    AppUser findByUsername(String username);

    Optional<AppUser> findByEmailAndSeller_Id(String email, Long sellerId);

    Optional<AppUser> findByMobileAndSeller_Id(String mobile, Long sellerId);



   // Optional<AppUser> findByMobileAndTenant_Id(String mobile, Long tenantId);

}
