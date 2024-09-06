package com.recetario.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private final UserAuthenticationEntryPoint userAuthenticationEntryPoint;

	@Autowired
	private final UserAuthProvider userAuthProvider;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.exceptionHandling(handling -> {
			try {
				handling.authenticationEntryPoint(userAuthenticationEntryPoint).and()
						.addFilterBefore(new JwtAuthFilter(userAuthProvider), BasicAuthenticationFilter.class)
						.csrf(csrf -> csrf.disable())
						.sessionManagement(
								management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
						.authorizeHttpRequests(
								(requests) -> requests.requestMatchers(HttpMethod.POST, "/login", "/register", "/users")
										.permitAll().anyRequest().authenticated());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		return http.build();
	}

}
