package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AppUserDetailsService implements UserDetailsService {

        private final PersonRepository repository;

        @Override
        public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            Person person = repository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
            return User.withUsername(person.getEmail())
                .password(person.getPassword())
                .roles("USER")
                .build();
        }
}
