package com.example.demo.service;

import com.example.demo.TestBase;
import com.example.demo.dto.PersonDTO;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

public class PersonServiceTest extends TestBase {
        private final PersonRepository personRepository = Mockito.mock(PersonRepository.class);
        private final PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
        PersonService personService = new PersonService(personRepository, passwordEncoder);

        @Test
        void testFindPersonByEmailSuccessful() {
                Person person = new Person();
                person.setId(1L).setEmail("bender@gmail.com").setLogin("bender");
                Mockito.when(personRepository.findByEmail("bender@gmail.com"))
                    .thenReturn(Optional.of(person));
                Person result = personService.findPersonByEmail("bender@gmail.com");
                Assertions.assertEquals(result.getLogin(), "bender");
        }

        @Test
        void testFindPersonByEmailFailed() {
                Person person = new Person();
                person.setId(1L).setEmail("fake@gmail.com").setLogin("fake");
                Mockito.when(personRepository.findByEmail("fake@gmail.com"))
                    .thenReturn(Optional.empty());

                assertThrows(NoSuchElementException.class, () -> personService.findPersonByEmail("fake@gmail.com"));
        }

        @Test
        void savePerson() {
                PersonDTO person = new PersonDTO();
                person.setId(1L).setEmail("bender@gmail.com").setLogin("bender").setPassword("123");

                ArgumentCaptor<Person> argumentCaptor = ArgumentCaptor.forClass(Person.class);

                personService.savePerson(person);

                verify(personRepository).save(argumentCaptor.capture());

                Assertions.assertEquals(argumentCaptor.getValue().getLogin(), "bender");
        }


}
