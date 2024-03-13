package ru.vk.internship.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.GET, "/api/ping").permitAll()

                        .requestMatchers(HttpMethod.GET, "/api/roles/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/roles/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/roles/**").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/accounts/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/accounts/**").permitAll()
                        .requestMatchers(HttpMethod.PATCH, "/api/accounts/**").permitAll() // for demo purposes
                        .requestMatchers(HttpMethod.DELETE, "/api/accounts/**").hasAnyAuthority("ADMIN")

                        .requestMatchers(HttpMethod.GET, "/api/posts/**").hasAnyAuthority("POSTS_VIEWER", "POSTS_EDITOR", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/posts/**").hasAnyAuthority("POSTS_EDITOR", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/posts/**").hasAnyAuthority("POSTS_EDITOR", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/posts/**").hasAnyAuthority("POSTS_EDITOR", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/users/**").hasAnyAuthority("USERS_VIEWER", "USERS_EDITOR", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/users/**").hasAnyAuthority("USERS_EDITOR", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/users/**").hasAnyAuthority("USERS_EDITOR", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasAnyAuthority("USERS_EDITOR", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/albums/**").hasAnyAuthority("ALBUMS_VIEWER", "ALBUMS_EDITOR", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/albums/**").hasAnyAuthority("ALBUMS_EDITOR", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/albums/**").hasAnyAuthority("ALBUMS_EDITOR", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/albums/**").hasAnyAuthority("ALBUMS_EDITOR", "ADMIN")

                        .anyRequest().authenticated()
                )
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(withDefaults())
                .logout(withDefaults())
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(daoAuthenticationProvider);
    }
}
