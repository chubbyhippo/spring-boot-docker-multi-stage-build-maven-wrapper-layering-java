package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {DemoApplication.class})
@AutoConfigureWebTestClient
class DemoApplicationTests {

    @Test
    void shouldReturnHello(@Autowired WebTestClient webTestClient) {
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
