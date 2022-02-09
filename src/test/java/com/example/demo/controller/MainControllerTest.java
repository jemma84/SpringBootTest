package com.example.demo.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.example.demo.TestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;


public class MainControllerTest extends TestBase {
        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private MainController controller;

        @Test
        public void contextLoad() {
                assertThat(controller).isNotNull();
        }

        @Test
        void shouldAuthenticateUserAndReturnView() throws Exception {

                mockMvc.perform(get("/"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("mainPage"));

        }
}
