package com.example.demo;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

@SpringBootTest
//@AutoConfigureMockMvc
//@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"}, executionPhase = BEFORE_TEST_METHOD)
//@WithMockUser(username = "bender", password = "1qaz", roles = "USER")
public class TestBase {
}
