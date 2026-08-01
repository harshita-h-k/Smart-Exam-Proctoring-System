package com.smartexam.backend.config;

import com.smartexam.backend.security.CustomUserDetailsService;
import com.smartexam.backend.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth

                        // Public APIs
                        .requestMatchers("/api/auth/**").permitAll()

                        // Admin APIs
                        .requestMatchers("/api/admins/**")
                        .hasRole("ADMIN")

                        // Principal APIs
                        .requestMatchers("/api/principals/**")
                        .hasAnyRole("ADMIN", "PRINCIPAL")

                        // Faculty APIs
                        .requestMatchers("/api/faculties/**")
                        .hasAnyRole("ADMIN", "PRINCIPAL", "FACULTY")

                        // Student APIs
                        .requestMatchers("/api/students/**")
                        .hasAnyRole("ADMIN", "PRINCIPAL", "FACULTY", "STUDENT")

                        // Exam APIs
                        .requestMatchers("/api/exams/**")
                        .authenticated()

                        // Question APIs
                        .requestMatchers("/api/questions/**")
                        .hasAnyRole("ADMIN", "PRINCIPAL", "FACULTY")

                        // Attendance APIs
                        .requestMatchers("/api/attendance/**")
                        .hasAnyRole("ADMIN", "PRINCIPAL", "FACULTY")

                        // Result APIs
                        .requestMatchers("/api/results/**")
                        .authenticated()

                        // Alert APIs
                        .requestMatchers("/api/alerts/**")
                        .hasAnyRole("ADMIN", "PRINCIPAL", "FACULTY")

                        // Notification APIs
                        .requestMatchers("/api/notifications/**")
                        .authenticated()

                        // Login History APIs
                        .requestMatchers("/api/login-history/**")
                        .hasAnyRole("ADMIN", "PRINCIPAL")

                        // Violation APIs
                        .requestMatchers("/api/violations/**")
                        .hasAnyRole("ADMIN", "PRINCIPAL")

                        // Any other request
                        .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}