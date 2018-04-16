package com.vsoft.mysoftware.security.jwt;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class TokenProvider {
	
	private final Logger log = LoggerFactory.getLogger(TokenProvider.class);
	
	private static final String AUTHORITIES_KEY = "auth";
	
	//variable constant untuk kunci rahasia
	private static final String SECRET_KEY = "0586435e62c8a73abac9135ffd57e114f921540a";
	
	//variable constant untuk valid token jika tidak remember me dalam waktu 24 jam
	private static final long TOKEN_VALID_SECOND = 86400;
	
	//variable constant untuk valid token untuk remember me dalam waktu 720 jam / 30 hari
	private static final long TOKEN_VALID_SECOND_REMEMBER_ME = 2592000;
	
	//pengali data token valid agar menjadi milisecond
	private static final long MILISECOND = 1000;
	
	private long tokenValidMiliSecond;
	private long tokenValidMiliSecondRememberMe;
	
	
	@PostConstruct
	public void init(){
		this.tokenValidMiliSecond = TOKEN_VALID_SECOND * MILISECOND;
		
		this.tokenValidMiliSecondRememberMe = TOKEN_VALID_SECOND_REMEMBER_ME * MILISECOND;
		
	}
	
	//method untuk membuat token
	public String buatToken (Authentication authentication, Boolean rememberMe) {
		
		//??
		String authorities = authentication.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(","));
		
		//set variable now 
		long now = (new Date()).getTime();
		//set variable validity
		Date validity;
		if (rememberMe){
			validity = new Date(now + this.tokenValidMiliSecondRememberMe);
		}else{
			validity = new Date(now + this.tokenValidMiliSecond);
			
		}
		
		//mengembalikan data token
		return Jwts.builder()
				.setSubject(authentication.getName())
				.claim(AUTHORITIES_KEY, authorities)
				.signWith(SignatureAlgorithm.HS512 , SECRET_KEY)
				.setExpiration(validity)
				.compact();
	}

	public boolean validateToken(String token) {
		
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.info("Invalid JWT signature.");
            log.trace("Invalid JWT signature trace: {}", e);
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
            log.trace("Invalid JWT token trace: {}", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            log.trace("Expired JWT token trace: {}", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            log.trace("Unsupported JWT token trace: {}", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            log.trace("JWT token compact of handler are invalid trace: {}", e);
        }
		
		return false;
	}

	public Authentication getAuthorities(String token) {
		
		Claims claims = Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token)
				.getBody();
		
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        
        User principal = new User(claims.getSubject(), "", authorities);
        
		return new UsernamePasswordAuthenticationToken(principal, token, authorities);
	}
	
	
	
}
