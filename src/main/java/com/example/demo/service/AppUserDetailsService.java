package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AppUserDetailsService implements UserDetailsService {
        @Autowired
        private PersonRepository repository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
           try {
                Person person = repository.findPersonByLogin(username);
                if (person != null) {
                        return User.withUsername(person.getLogin())
                            .password(person.getPassword())
                            .roles("USER")
                            .build();
                }
           } catch (Exception e) {
                   e.printStackTrace();
           }
            throw new UsernameNotFoundException(username);
        }
}
