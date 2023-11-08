package com.github.moinmarcell.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(a -> a
						.requestMatchers("/api/v1/auth/**").permitAll()
						.anyRequest().authenticated()
				)
				.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
				.oauth2Login(c -> c.successHandler(new CustomAuthenticationSuccessHandler()));
		return http.build();
	}

}
