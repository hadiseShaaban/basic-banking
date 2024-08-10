package com.example.basic_banking.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //authorization
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("h2-console/**").permitAll()
                        .requestMatchers(HttpMethod.GET).permitAll()
                        .requestMatchers(HttpMethod.POST, "/withdraw/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/deposit/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/create-account/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                        .anyRequest().authenticated()
                ).csrf(csrf -> csrf.disable())
                .httpBasic(withDefaults())// استفاده از احراز هویت پایه (Basic Authentication)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // پیکربندی مدیریت جلسه به حالت بدون حالت (Stateless)
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));


        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin-pass"))
                .roles("ADMIN")
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("user-pass"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(admin, user);//saved user and admin in that time into the memory for processes
    }

}