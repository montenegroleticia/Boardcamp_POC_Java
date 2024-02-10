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

import com.boardcamp.api.dtos.CustomersDTO;
import com.boardcamp.api.dtos.GamesDTO;
import com.boardcamp.api.dtos.RentalsDTO;
import com.boardcamp.api.models.CustomersModel;
import com.boardcamp.api.models.GamesModel;
import com.boardcamp.api.models.RentalsModel;
import com.boardcamp.api.repositories.CustomersRepository;
import com.boardcamp.api.repositories.GamesRepository;
import com.boardcamp.api.repositories.RentalsRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class RentalsIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private RentalsRepository rentalsRepository;

    @Autowired
    private GamesRepository gamesRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @BeforeEach
    @AfterEach
    void cleanUpDatabase() {
        rentalsRepository.deleteAll();
        gamesRepository.deleteAll();
        customersRepository.deleteAll();
    }

    @Test
    void givenValidRental_whenCreatingRental_thenCreatesRental() {
        GamesModel game = new GamesModel(
                new GamesDTO("Banco Imobiliário", "http://www.imagem.com.br/banco_imobiliario.jpg", 3, 1000));
        GamesModel createdGame = gamesRepository.save(game);

        CustomersModel customer = new CustomersModel(new CustomersDTO("João Alfredo", "01234567890"));
        CustomersModel createdCustomer = customersRepository.save(customer);

        RentalsDTO rentalDTO = new RentalsDTO(createdGame.getId(), createdCustomer.getId(), 3);

        HttpEntity<RentalsDTO> body = new HttpEntity<>(rentalDTO);

        ResponseEntity<RentalsModel> response = restTemplate.exchange("/rentals", HttpMethod.POST, body,
                RentalsModel.class);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
