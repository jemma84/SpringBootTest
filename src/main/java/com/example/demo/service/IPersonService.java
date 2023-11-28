package com.example.demo.service;

import com.example.demo.dto.PersonDTO;
import com.example.demo.model.Person;

public interface IPersonService {
    void savePerson(PersonDTO personDTO);

    Person findPersonByEmail(String email);
}
