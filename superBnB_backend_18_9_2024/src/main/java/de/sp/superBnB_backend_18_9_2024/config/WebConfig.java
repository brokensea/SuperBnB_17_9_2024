package de.sp.superBnB_backend_18_9_2024.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")  // 允许所有来源的跨域请求
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}
/*
跨域配置（全局设置）: 如果你希望对整个应用进行跨域配置，而不仅仅是某个控制器，
你可以在 Spring Boot 的配置类中设置。这通常适用于需要统一管理跨域策略的场景。*/