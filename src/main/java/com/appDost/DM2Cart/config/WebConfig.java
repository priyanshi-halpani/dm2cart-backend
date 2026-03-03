package com.appDost.DM2Cart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply to all endpoints
                .allowedOrigins("http://localhost:8088","http://localhost:5174", "http://localhost:5173") // Expo's dev server URL (Change this to match your Expo server's URL)
                .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS") // Allow methods for the request
                .allowedHeaders("Content-Type", "Authorization", "Accept", "X-Requested-With") // Allow specific headers
                .allowCredentials(true);
    }

}
