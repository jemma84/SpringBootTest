package com.example.demo.service;

import com.example.demo.dao.IPersonDAO;
import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PersonService implements IPersonDAO {
        @Autowired
        private PersonRepository repository;

        @Override
        public Person getPerson() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                return repository.findPersonByLogin(authentication.getName());
        }

        public Person savePerson(Person person) {
                Person updatedPerson =  getPerson();
                updatedPerson.setLogin(person.getLogin());
                updatedPerson.setSectors(person.getSectors());
                return repository.save(updatedPerson);
        }


}
