package com.te.booking.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.te.booking.util.JwtUtil;



@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtil util;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request,
			HttpServletResponse response, 
			FilterChain filterChain)
			throws ServletException, 
			IOException 
	{
		//Read token from Auth header
		String token=request.getHeader("Authorization");
		if(token!=null) {
			//do validation
			String username =util.getUsername(token);
			//username should not be empty ,context-auth must be empty
			if(username!=null &&
					SecurityContextHolder.getContext()
					.getAuthentication()==null)
			{
			UserDetails user=userDetailsService.loadUserByUsername(username);
			//validate token
			boolean isvalid=util.validateToken(token, user.getUsername());
			
			if(isvalid) {
				UsernamePasswordAuthenticationToken authtoken=
						new UsernamePasswordAuthenticationToken(
								username, 
								user.getPassword(),user.getAuthorities());
				
				authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				//final object stored in security context with userdetails(name,psw)
				SecurityContextHolder.getContext().setAuthentication(authtoken);
			}
			}
		}
		filterChain.doFilter(request, response);
	}

}


