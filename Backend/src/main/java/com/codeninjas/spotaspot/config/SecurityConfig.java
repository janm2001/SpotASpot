package com.codeninjas.spotaspot.config;

import com.codeninjas.spotaspot.users.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/auth/**").permitAll()
                .requestMatchers("/api/v1/user/get").authenticated()
                .requestMatchers("/api/v1/event/all").permitAll()
                .requestMatchers("/api/v1/event/for-user/{id}").permitAll()
                .requestMatchers("/api/v1/event/{id}").permitAll()
                .requestMatchers("/api/v1/event/add").hasAnyRole(Role.ORGANIZER.name(), Role.ADMIN.name())
                .requestMatchers("/api/v1/event/delete/{id}").hasAnyRole(Role.ORGANIZER.name(), Role.ADMIN.name())
                .requestMatchers("/api/v1/event/update").hasAnyRole(Role.ORGANIZER.name(), Role.ADMIN.name())
                .requestMatchers("/api/v1/event/categories").permitAll()
                .anyRequest().denyAll()
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
