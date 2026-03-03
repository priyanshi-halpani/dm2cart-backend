package com.appDost.DM2Cart.config;

import com.appDost.DM2Cart.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http

                // JWT based APIs -> Stateless
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())

                .authorizeHttpRequests(auth -> auth

                        // ✅ Swagger / OpenAPI (FULLY PUBLIC)
                        .requestMatchers(
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**"
                        ).permitAll()

                        // ✅ OPTIONS request (CORS / Browser / Swagger)
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // ✅ Public APIs
                        .requestMatchers("/auth/**", "/home/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/tenants/*/auth/**").permitAll()

                        // ---- Product APIs ----
                        .requestMatchers(HttpMethod.POST, "/api/products/**")
                        .hasRole("SELLER")

                        .requestMatchers(HttpMethod.PUT, "/api/products/**")
                        .hasRole("SELLER")

                        .requestMatchers(HttpMethod.DELETE, "/api/products/**")
                        .hasRole("SELLER")

                        .requestMatchers(HttpMethod.GET, "/api/products/**")
                        .hasAnyRole("SELLER", "CUSTOMER")

                        // ---- Seller Profile APIs ----
                        .requestMatchers("/seller/profile/**")
                        .hasRole("SELLER")

                        // 🔒 Everything else secured
                        .anyRequest().authenticated()
                );

        // ✅ JWT filter

        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
