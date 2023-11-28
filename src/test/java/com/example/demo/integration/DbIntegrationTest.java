package com.example.demo.integration;

import com.example.demo.dto.PersonDTO;
import com.example.demo.model.Person;
import com.example.demo.repository.SectorRepository;
import com.example.demo.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DbIntegrationTest {
    @Autowired
    PersonService personService;

    @Autowired
    SectorRepository sectorRepository;
    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13.2")
        .withDatabaseName("integration-tests-db").withUsername("postgres").withPassword("postgres")
        .withInitScript("db/test-data.sql");

    static {
        postgreSQLContainer.start();
    }

    @DynamicPropertySource
    static void props(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Test
    void test1() {
        Assertions.assertTrue(postgreSQLContainer.isCreated());
        Assertions.assertTrue(postgreSQLContainer.isRunning());
    }

    @Test
    void savePersonSuccessful() {
        PersonDTO person = new PersonDTO();
        person.setEmail("emmy@gmail.com").setLogin("emmy").setPassword("777");

        personService.savePerson(person);
        Person person1 = personService.findPersonByEmail("emmy@gmail.com");
        Assertions.assertEquals(2L, person1.getId());

    }
}
