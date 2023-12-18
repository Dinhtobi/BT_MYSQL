package com.springSecurity.Config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springSecurity.Service.IUserDetailService;
import com.springSecurity.Service.Impl.UserDetailService;
import com.springSecurity.utils.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtAuthenticaionFilter extends OncePerRequestFilter{
	
	private final UserDetailService userDetailService;
	
	private final JwtUtil jwt;
	
	
	public JwtAuthenticaionFilter(UserDetailService userDetailService, JwtUtil jwt) {
		super();
		this.userDetailService = userDetailService;
		this.jwt = jwt;
	}
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = getToken(request);
		try {
			if(token != null) {
				String username = jwt.extractUsername(token);
				if(username != null && SecurityContextHolder.getContext().getAuthentication() == null ) {
					UserDetails userDetails = userDetailService.loadUserByUsername(username);
					if(userDetails != null) {
						UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null , userDetails.getAuthorities());
						SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					}
				}
			}
			
		} catch (Exception e) {
			log.info("token Invalid");
		}
		doFilter(request, response, filterChain);
	}
	
	public String getToken(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if(bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

}
