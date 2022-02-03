package com.example.demo.config;

import com.example.demo.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
        @Autowired
        private AppUserDetailsService appUserDetailsService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
                     http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/h2/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll();

                //use H2 web console
                http.headers().frameOptions().disable();
        }

        @Override
        public void configure(WebSecurity web) {
                web.ignoring()
                    .antMatchers( "/resources/**", "/static/**", "/css/**");
        }


        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
                auth.userDetailsService(appUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
        }
}
