package com.skilling.lms.users_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
            .csrf(ServerHttpSecurity.CsrfSpec::disable) // Deshabilización del CSRF
            .authorizeExchange(exchanges -> exchanges
                .anyExchange().permitAll()
            );
        return http.build();
    }

    /// TODO: Usar PasswordEncoder (BCryptoPasswordEncoder)
    /// @Bean
    /// public PasswordEncoder passwordEncoder() {
    ///     return new BCryptoPasswordEncoder();
    /// }

    /// TODO: Utilizar un ReactiveUserDetailsService para cargar los usuarios de la DB
    /// @Bean
    /// public ReactiveUserDetailsService userDetailService(UserRepository userRepository) {
    ///     return username -> userRepository.findByUsername(username)
    ///         .map(user -> User.builder()
    ///             .username(user.getUsername)
    ///             .password(user.getPasswordHash()) // Debe estar encriptado
    ///             .roles(user.getRoles().toArray(new String[0])) // Se debe utilizar una lista de roles
    ///             .build()
    ///         );
    /// }
}
