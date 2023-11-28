package com.example.demo.service;

import com.example.demo.dto.PersonDTO;
import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PersonService implements IPersonService {
        private final PersonRepository repository;
        private final PasswordEncoder passwordEncoder;


        public Person getCurrentPerson() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                return repository.findByEmail(authentication.getName()).orElseThrow();
        }

        public Person updatePersonData(PersonDTO person) {
                Person updatedPerson =  getCurrentPerson();
                updatedPerson.setLogin(person.getLogin());
                updatedPerson.setSectors(person.getSectors());
                return repository.save(updatedPerson);
        }


        @Override public void savePerson(PersonDTO personDTO) {
                String hashedPassword = passwordEncoder.encode(personDTO.getPassword());
                repository.save(new Person()
                                    .setLogin(personDTO.getLogin())
                                    .setEmail(personDTO.getEmail())
                                    .setPassword(hashedPassword));
        }

        @Override
        public Person findPersonByEmail(String email) {
                return repository.findByEmail(email).orElseThrow();
        }
}
