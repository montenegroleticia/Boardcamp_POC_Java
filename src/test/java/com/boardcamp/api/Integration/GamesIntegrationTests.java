package com.boardcamp.api.Integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.boardcamp.api.dtos.GamesDTO;
import com.boardcamp.api.models.GamesModel;
import com.boardcamp.api.repositories.GamesRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class GamesIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private GamesRepository gamesRepository;

    @BeforeEach
    @AfterEach
    void cleanUpDatabase() {
        gamesRepository.deleteAll();
    }

    @Test
    void givenValidGame_whenCreatingGame_thenCreatesGame() {
        GamesDTO gameDTO = new GamesDTO("Banco Imobiliário", "http://www.imagem.com.br/banco_imobiliario.jpg", 3, 1000);

        HttpEntity<GamesDTO> body = new HttpEntity<>(gameDTO);

        ResponseEntity<GamesModel> response = restTemplate.exchange("/games", HttpMethod.POST, body, GamesModel.class);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
