package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import java.io.UnsupportedEncodingException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DemoApplicationTests {

    @Test
    @DisplayName("should return hello using mock mvc tester")
    void shouldReturnHelloUsingMockMvcTester(@Autowired MockMvcTester mockMvcTester) throws UnsupportedEncodingException {
        var response = mockMvcTester.get()
                .uri("/")
                .exchange()
                .getMvcResult()
                .getResponse();

        var contentAsString = response.getContentAsString();

        assertThat(contentAsString)
                .isEqualTo("Hello");
    }

    @Test
    @DisplayName("should return hello using web test client")
    void shouldReturnHelloUsingWebTestClient(@Autowired WebTestClient webTestClient) {
        var responseBody = webTestClient.get()
                .uri("/")
                .exchange()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();

        assertThat(responseBody)
                .isEqualTo("Hello");
    }

}
