package com.codeninjas.spotaspot.config;

import com.codeninjas.spotaspot.auth.service.JWTAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    //@Qualifier("delegatedAuthEntryPoint")
    private final AuthenticationEntryPoint delegatedAuthEntryPoint;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/api/v1/user/get").authenticated()
                .requestMatchers("/api/v1/event/all").permitAll()
                .requestMatchers("/api/v1/event/for-user/{userId}").permitAll()
                .requestMatchers("/api/v1/event/get/{eventId}").permitAll()
                .requestMatchers("/api/v1/event/add").hasAnyAuthority("ORGANIZER")
                .requestMatchers("/api/v1/event/delete/{eventId}").hasAnyAuthority("ORGANIZER")
                .requestMatchers("/api/v1/event/update").hasAnyAuthority("ORGANIZER")
                .requestMatchers("/api/v1/event/categories").permitAll()
                .requestMatchers("/actuator/**").permitAll()
                .requestMatchers("/api/v3/api-docs").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(delegatedAuthEntryPoint);
        return http.build();
    }
}
