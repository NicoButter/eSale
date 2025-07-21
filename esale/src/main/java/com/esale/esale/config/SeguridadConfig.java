package com.esale.esale.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SeguridadConfig {

        // @Bean
        // public WebSecurityCustomizer webSecurityCustomizer() {
        // return (web) -> web.ignoring().requestMatchers(
        // "/index.html",
        // "/",
        // "/**/*.js",
        // "/**/*.css",
        // "/assets/**",
        // "/favicon.ico");
        // }

        @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/", "/index.html",
                    "/assets/**", "/styles.css",
                    "/*.js", "/*.css", "/*.ico",
                    "/articulos/**", "/destacados", "/ofertas"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(withDefaults()) // usamos login default por ahora
            .logout(logout -> logout.permitAll())
            .csrf(csrf -> csrf.disable());

        return http.build();
    }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
