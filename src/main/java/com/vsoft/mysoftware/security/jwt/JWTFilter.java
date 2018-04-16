package com.vsoft.mysoftware.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

public class JWTFilter extends GenericFilterBean {

	private TokenProvider tokenProvider;
	
	public JWTFilter(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	
		HttpServletRequest httpServerRequest = (HttpServletRequest) request;
		
		//ambil token
		String jwt = resolveToken(httpServerRequest);
		
		//cek token termasuk data text dan validasi token
		if(StringUtils.hasText(jwt) && this.tokenProvider.validateToken(jwt)){
			Authentication authentication = this.tokenProvider.getAuthorities(jwt);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		chain.doFilter(request, response);
	}
	
	// method mengembalikan token
	private String resolveToken(HttpServletRequest request){
		
		//mengambil header dengan nama Authorization
		String bearerToken = request.getHeader(JWTConfigurer.AUTHORIZATION_HEADER);
		
		//cek data bearerToken
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
			 return bearerToken.substring(7, bearerToken.length());
		}
		
		return null;
	}

	
	
}
