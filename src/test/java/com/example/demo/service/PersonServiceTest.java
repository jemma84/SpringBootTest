package com.example.demo.service;

import com.example.demo.TestBase;
import com.example.demo.model.Sector;
import com.example.demo.repository.SectorRepository;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonServiceTest extends TestBase {
        @Autowired
        PersonService personService;

        @Autowired
        SectorRepository sectorRepository;

        @Autowired
        private PersonRepository personRepository;

        @Test
        void testFindpersonByNameAndSaveChoise() {

                Person returnPerson = personRepository.findPersonByLogin("bender");

                Assertions.assertNotNull(returnPerson);


                Optional<Sector> sector = sectorRepository.findById(42L);
                Assertions.assertNotNull(sector);

                List<Sector> list = new ArrayList<>();
                list.add(sector.get());
                returnPerson.setSectors(list);
                Person updatedPerson = personService.savePerson(returnPerson);
                assertEquals(updatedPerson.getSectors().size(), 1);

        }


}
