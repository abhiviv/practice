package com.management.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class Webconfig implements WebMvcConfigurer{
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
    
	@Bean
    public AuditorAware<Object> auditorProvider() {

    	return () -> {
    		if (SecurityContextHolder.getContext().getAuthentication() != null) {
    			return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    		} else {
    			return Optional.ofNullable("ADMIN");
    		}
    	};
    }
}
