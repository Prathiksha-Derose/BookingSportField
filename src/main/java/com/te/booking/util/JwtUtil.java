package com.te.booking.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	@Value("${app.secret}")
		private String secret;
		
		
		
	//1.Generate Token
		public String generateToken(String subject)
		{
			return Jwts.builder()
					.setSubject(subject)
					.setIssuer("Derose")
					.setIssuedAt(new Date(System.currentTimeMillis()))
					.setExpiration(new Date(System.currentTimeMillis()+TimeUnit.MINUTES.toMillis(15)))
					.signWith(SignatureAlgorithm.HS512, secret.getBytes())
					.compact();
		}
		//2.Read claims
		public Claims getClaims(String token) {
			return Jwts.parser()
					.setSigningKey(secret.getBytes())
					.parseClaimsJwt(token)
					.getBody();
			
		}
		
		//3.Read Exp date
		public Date getExpDate(String token) {
			return getClaims(token).getExpiration();
			
		}
		
		//4.Read Subject/username
		public String getUsername(String token) {
			return getClaims(token).getSubject();
		}
		
		//5.Validate Exp Date
		public boolean isTokenExp(String token) {
			Date expDate=getExpDate(token);
			return expDate.before(new Date(System.currentTimeMillis()));
		}
		
		//6.Validate user name in token and database,expDate
		public boolean validateToken(String token,String username) {
			String tokenuserName=getUsername(token);
			return (username.equals(tokenuserName)&& !isTokenExp(token));
			
		}
	}


