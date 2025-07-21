package com.esale.esale.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SeguridadConfig {

        @Bean
        public WebSecurityCustomizer webSecurityCustomizer() {
                return (web) -> web.ignoring().requestMatchers(
                                "/index.html",
                                "/",
                                "/**/*.js",
                                "/**/*.css",
                                "/assets/**",
                                "/favicon.ico");
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(authorize -> authorize
                                                .requestMatchers(
                                                                "/",
                                                                "/index.html",
                                                                "/**/*.js",
                                                                "/**/*.css",
                                                                "/assets/**",
                                                                "/favicon.ico",
                                                                "/destacados/**", // <--- IMPORTANTE: estas rutas
                                                                                  // públicas
                                                                "/offers/**"
                                                ).permitAll()
                                                .anyRequest().authenticated() // lo demás requiere login
                                )
                                .formLogin(form -> form
                                                .loginPage("/usuario/login")
                                                .permitAll()
                                                .defaultSuccessUrl("/", true))
                                .logout(logout -> logout
                                                .permitAll()
                                                .logoutSuccessUrl("/"))
                                .csrf(csrf -> csrf.disable());
                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
