package com.vsoft.mysoftware.config;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;

import com.vsoft.mysoftware.security.jwt.JWTConfigurer;
import com.vsoft.mysoftware.security.jwt.TokenProvider;




@Configuration
@Import(SecurityProblemSupport.class)
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    
    private final UserDetailsService userDetailsService;
    
    private final TokenProvider  tokenProvider;
    
    private final SecurityProblemSupport securityProblemSupport;
    
    
	public SecurityConfiguration(
			AuthenticationManagerBuilder authenticationManagerBuilder,
			UserDetailsService userDetailsService,
			TokenProvider tokenProvider,
			SecurityProblemSupport securityProblemSupport
			){
		this.authenticationManagerBuilder = authenticationManagerBuilder;
		this.userDetailsService = userDetailsService;
		this.tokenProvider = tokenProvider;
		this.securityProblemSupport = securityProblemSupport;
	}
	
	@PostConstruct
	public void init(){
		try {
            authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        } catch (Exception e) {
            throw new BeanInitializationException("Security configuration failed", e);
        }
	}
	
	
    @Override
    public void configure(HttpSecurity http) throws Exception {
    	http
//    	 	.addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class)
            .exceptionHandling()
            .authenticationEntryPoint(securityProblemSupport)
            .accessDeniedHandler(securityProblemSupport)
        .and()
    		.csrf()
    		.disable()
    		.headers()
    		.frameOptions()
    		.disable()
    	.and()
    		.sessionManagement()
    		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    	.and()
    		.authorizeRequests()
    		.antMatchers("/api/authenticate").permitAll()
    		.antMatchers("/api/mheadermenus").permitAll()
    		.anyRequest().authenticated()
    	.and()
    		.apply(securityConfigurerAdapter());
    		
    }
    
    private JWTConfigurer securityConfigurerAdapter() {
        return new JWTConfigurer(tokenProvider);
    }
    
	//custome password endcode
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
//    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
   
/*    @Bean
    public CorsFilter corsFilter() throws Exception {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
            source.registerCorsConfiguration("/api/**", config);
            config.addAllowedOrigin("*");
        }
        return new CorsFilter(source);
    }*/
    
    
    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }

}
