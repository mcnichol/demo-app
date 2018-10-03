package com.mcnichol.demo.controller;


import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RestController;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AppControllerTest {

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new AppControllerImpl(new CompositeMeterRegistry())).build();
    }

    @Test
    void hasRestControllerAnnotation() { assertTrue(AppControllerImpl.class.isAnnotationPresent(RestController.class));
    }

    @Test
    void doesWork() throws Exception {
        String contentAsString = mockMvc.perform(get("/hello")).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertThat(contentAsString, equalTo("World"));
    }
}