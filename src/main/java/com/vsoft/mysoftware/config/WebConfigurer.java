/*package com.vsoft.mysoftware.config;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebConfigurer implements ServletContextInitializer {
	
	  @Bean
	    public CorsFilter corsFilterBean() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        CorsConfiguration config = new CorsConfiguration();
	        if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
	            source.registerCorsConfiguration("/api/**", config);
	        }
	        return new CorsFilter(source);
	    }
}
*/