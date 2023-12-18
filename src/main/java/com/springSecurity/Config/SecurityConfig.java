package com.springSecurity.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.springSecurity.Service.IUserDetailService;
import com.springSecurity.Service.Impl.UserDetailService;
import com.springSecurity.utils.JwtUtil;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationEntrypoint entrypoint;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private  JwtUtil jwt;
	
	@Autowired UserDetailService userDetailService;
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.cors(AbstractHttpConfigurer::disable);
	    http.csrf(AbstractHttpConfigurer::disable)
		.exceptionHandling(exception -> exception.authenticationEntryPoint(entrypoint))
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(auth -> {
			auth.requestMatchers("/user/**").permitAll();
			auth.requestMatchers("/admin/**").hasRole("ADMIN");
			auth.anyRequest().authenticated();
		});
		http.addFilterBefore(new JwtAuthenticaionFilter( userDetailService,jwt), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
