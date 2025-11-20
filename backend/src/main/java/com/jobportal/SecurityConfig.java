package com.jobportal;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jobportal.jwt.JwtAuthenticationEntryPoint;
import com.jobportal.jwt.JwtAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login","/users/register", "/users/verifyOtp/**","/users/sendOtp/**","/users/changePass").permitAll()
                        // Employer-only endpoints
                        .requestMatchers("/jobs/post", "/jobs/postAll", "/jobs/postedBy/**", "/jobs/changeAppStatus").hasRole("EMPLOYER")
                        // Applicant-only endpoints
                        .requestMatchers("/jobs/apply/**", "/jobs/history/**").hasRole("APPLICANT")
                        // Shared read endpoints for both roles
                        .requestMatchers("/jobs/getAll", "/jobs/get/**").hasAnyRole("APPLICANT","EMPLOYER")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}
