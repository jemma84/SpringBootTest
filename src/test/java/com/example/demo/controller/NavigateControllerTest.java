package com.example.demo.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.example.demo.TestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class NavigateControllerTest extends TestBase {
        private MockMvc mockMvc;

        @BeforeEach
        public void setup() {
                this.mockMvc = MockMvcBuilders.standaloneSetup(new NavigateController()).build();
        }


        @Test
        void shouldAuthenticateUserAndReturnView() throws Exception {

                mockMvc.perform(get("/"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("homePage"));

        }
}
