package com.recetario.backend.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class WebConfig {

    @Bean
    FilterRegistrationBean<?> corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:3333");
		config.setAllowedHeaders(
				Arrays.asList(HttpHeaders.AUTHORIZATION, HttpHeaders.CONTENT_TYPE, HttpHeaders.ACCEPT));
		config.setAllowedMethods(Arrays.asList(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
				HttpMethod.DELETE.name()));
		config.setMaxAge(3600L); // Vida máxima de los permisos (1 hora)
		source.registerCorsConfiguration("/**", config);
		
		/*
		 * Establece el orden de ejecución del filtro. El orden es un número entero que
		 * indica en qué secuencia se ejecutará el filtro en relación con otros filtros
		 * en la cadena de filtros. En este caso, se establece un orden específico de
		 * -102. Los filtros con órdenes más bajos se ejecutarán antes que los filtros
		 * con órdenes más altos. Un orden negativo como -102 puede utilizarse para
		 * especificar un orden antes de los filtros por defecto.
		 */
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
		bean.setOrder(-102);
		/*
		 * 
		 * */

		return bean;
	}
}
