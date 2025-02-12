package com.onion.backend.config;

import com.onion.backend.jwt.JwtAuthenticationFilter;
import com.onion.backend.jwt.JwtUtil;
import com.onion.backend.service.CustomUserDetailsService;
import com.onion.backend.service.JwtBlacklistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Autowired
	private CustomUserDetailsService userDetailService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private JwtBlacklistService jwtBlacklistService;

	@Bean
	public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
			.sessionManagement(
				(sessionManagement) -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			)
			.authorizeHttpRequests(
				(requests) -> requests.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/api/users/sign-up", "/api/users/login", "/api/users/token/validation")
					.permitAll()
					.anyRequest()
					.authenticated()
			)
			.addFilterBefore(new JwtAuthenticationFilter(jwtUtil, userDetailService, jwtBlacklistService), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(final HttpSecurity http) throws Exception {
		final AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(
			AuthenticationManagerBuilder.class);

		authenticationManagerBuilder.userDetailsService(userDetailService)
			.passwordEncoder(passwordEncoder());

		return authenticationManagerBuilder.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
