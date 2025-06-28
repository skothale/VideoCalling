package com.example.videocallingbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns(
                    "https://localhost:3000",
                    "http://localhost:3000",
                    "https://192.168.1.7:3000",
                    "http://192.168.1.7:3000",
                    "https://127.0.0.1:3000",
                    "http://127.0.0.1:3000",
                    "https://video-calling-frontend-black.vercel.app",
                    "https://video-calling-frontend.vercel.app",
                    "https://*.vercel.app",
                    "https://vercel.app"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("https://localhost:3000");
        configuration.addAllowedOriginPattern("http://localhost:3000");
        configuration.addAllowedOriginPattern("https://192.168.1.7:3000");
        configuration.addAllowedOriginPattern("http://192.168.1.7:3000");
        configuration.addAllowedOriginPattern("https://127.0.0.1:3000");
        configuration.addAllowedOriginPattern("http://127.0.0.1:3000");
        configuration.addAllowedOriginPattern("https://video-calling-frontend-black.vercel.app");
        configuration.addAllowedOriginPattern("https://video-calling-frontend.vercel.app");
        configuration.addAllowedOriginPattern("https://*.vercel.app");
        configuration.addAllowedOriginPattern("https://vercel.app");
        configuration.setAllowedMethods(java.util.Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(java.util.Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
} 