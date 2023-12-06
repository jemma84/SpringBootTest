package com.example.demo.config;

import com.example.demo.handler.CustomAuthenticationEntryPoint;
import com.example.demo.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
        @Autowired
        private AppUserDetailsService appUserDetailsService;

        @Autowired
        private PasswordEncoder passwordEncoder;
        @Autowired
        private CustomAuthenticationEntryPoint authenticationEntryPoint;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http.csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/home", "/signIn", "/register", "/registerUser", "/error").permitAll()
                        .anyRequest().authenticated()
                    )
                    .formLogin()
                    .defaultSuccessUrl("/userSectors",true)
                    .and()
                    .logout().logoutSuccessUrl("/")
                    .and()
                    .rememberMe()
                    .and()
                    .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);

                return http.build();
        }

        @Bean
        public AuthenticationProvider authenticationProvider() {
                DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
                authenticationProvider.setUserDetailsService(appUserDetailsService);
                authenticationProvider.setPasswordEncoder(passwordEncoder);
                return authenticationProvider;
        }

        @Bean
        public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
                return http.getSharedObject(AuthenticationManagerBuilder.class)
                    .build();
        }

}
