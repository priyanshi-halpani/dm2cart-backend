package com.appDost.DM2Cart.config;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.appDost.DM2Cart.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    private String parseJwt(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        System.out.println("JwtAuthFilter HIT: " + request.getRequestURI());

        String uri = request.getRequestURI();

        // PUBLIC ENDPOINTS
        if (uri.startsWith("/auth/") || uri.startsWith("/tenants/")
                || uri.startsWith("/swagger") || uri.startsWith("/v3/api-docs")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = parseJwt(request);

            if (StringUtils.hasText(token) && jwtUtil.validateToken(token)) {
                Claims claims = jwtUtil.getClaims(token);
                String username = claims.getSubject();

//                String tenantIdStr = claims.get("tenantId", String.class);
//                if (tenantIdStr != null) {
//                    try {
//                        Long tenantId = Long.parseLong(tenantIdStr);
//                        TenantContext.setTenant(tenantId);
//                    } catch (NumberFormatException e) {
//                        // ignore
//                    }
//                }

                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);

                // ✅ Safe debug log
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (auth != null) {
                    System.out.println("Authenticated user: " + auth.getName());
                    System.out.println("Authorities: " + auth.getAuthorities());
                }

            } else {
                System.out.println("No JWT token or invalid token for request: " + uri);
            }

            filterChain.doFilter(request, response);

        } catch (Exception ex) {
            SecurityContextHolder.clearContext();
            filterChain.doFilter(request, response);
        }
//        finally {
//            TenantContext.clear();
//        }
    }

}
