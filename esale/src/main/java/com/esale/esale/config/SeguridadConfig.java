package com.esale.esale.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SeguridadConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/index.html", "/**/*.js", "/**/*.css", "/assets/**").permitAll() // Permitir acceso a la landing y estáticos
                .requestMatchers("/articulos/**").authenticated() // Proteger rutas de artículos
                .anyRequest().authenticated() // Otras rutas requieren autenticación
            )
            .formLogin(formLogin -> formLogin
                .loginPage("/login") // Página de login personalizada
                .permitAll()
                .defaultSuccessUrl("/", true) // Redirigir a / tras login exitoso
            )
            .logout(logout -> logout
                .permitAll()
                .logoutSuccessUrl("/") // Redirigir a / tras logout
            )
            .csrf(csrf -> csrf.disable()); // Desactivar CSRF temporalmente (opcional, reactívalo  para formularios)
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}