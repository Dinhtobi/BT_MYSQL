package com.assignment.java.Config;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.assignment.java.Security.JwtAuthenticationEntryPoint;
import com.assignment.java.Security.JwtAuthenticationFilter;
import com.assignment.java.Service.Impl.UserDetailService;
import com.assignment.java.Utils.JwtUtil;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint entrypoint;
	
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
			auth.requestMatchers("/account/**").permitAll();
			auth.requestMatchers("/admin/**").hasRole("ADMIN");
			auth.requestMatchers("/**").authenticated().anyRequest().authenticated();
		});
		http.addFilterBefore(new JwtAuthenticationFilter( userDetailService,jwt), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
	
}


