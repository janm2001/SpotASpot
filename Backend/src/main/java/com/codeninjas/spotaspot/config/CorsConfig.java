package com.codeninjas.spotaspot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

    @Value("#{'${cors.allowed-origins}'.replaceAll(\"\\\\s+\",\"\").split(',')}")
    private List<String> allowedOrigins;

    @Value("#{'${cors.allowed-methods}'.replaceAll(\"\\\\s+\",\"\").split(',')}")
    private List<String> allowedMethods;

    @Value("#{'${cors.allowed-headers}'.replaceAll(\"\\\\s+\",\"\").split(',')}")
    private List<String> allowedHeaders;

    @Value("#{'${cors.exposed-headers}'.replaceAll(\"\\\\s+\",\"\").split(',')}")
    private List<String> exposedHeaders;

    @Bean
    public CorsFilter corsFilter() {
        System.out.println(allowedMethods);
        CorsConfiguration configuration = new CorsConfiguration();
        allowedOrigins.forEach(configuration::addAllowedOrigin);
        allowedHeaders.forEach(configuration::addAllowedHeader);
        allowedMethods.forEach(configuration::addAllowedMethod);
        exposedHeaders.forEach(configuration::addExposedHeader);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return new CorsFilter(source);
    }
}

