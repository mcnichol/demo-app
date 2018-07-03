package com.mcnichol.demo.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.RestController;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppControllerTestRestTemplateIntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void hasRestControllerAnnotation() {
        assertTrue("Has RestController annotation", AppControllerImpl.class.isAnnotationPresent(RestController.class));
    }

    @Test
    void helloReturns200() {

        ResponseEntity<String> response = testRestTemplate.getForEntity("/hello", String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test
    void protectedHelloReturns200() {
        ResponseEntity<String> response = testRestTemplate
                .withBasicAuth("user", "password")
                .getForEntity("/password/hello", String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
}