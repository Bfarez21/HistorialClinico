package ista.sistemaClinica.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
public class WebConfig implements  WebMvcConfigurer{
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // permite todas las rutas
                .allowedOrigins("http://localhost:4200","http://34.42.10.91:8281") // tu frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}
