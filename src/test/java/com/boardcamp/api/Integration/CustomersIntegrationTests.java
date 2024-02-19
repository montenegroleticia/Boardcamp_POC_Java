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
import com.boardcamp.api.models.CustomersModel;
import com.boardcamp.api.repositories.CustomersRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CustomersIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CustomersRepository customersRepository;

    @BeforeEach
    @AfterEach
    void cleanUpDatabase() {
        customersRepository.deleteAll();
    }

    @Test
    void givenRepeteadCPF_whenCreatingCustomer_thenThrowsConflictError() {
        CustomersDTO customerDTO = new CustomersDTO("João Alfredo", "01234567890");
  
        CustomersModel customer = new CustomersModel(customerDTO);
        customersRepository.save(customer);

        HttpEntity<CustomersDTO> body = new HttpEntity<>(customerDTO);

        ResponseEntity<String> response = restTemplate.exchange("/customers", HttpMethod.POST, body, String.class);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }

    @Test
    void givenValidCustomer_whenCreatingCustomer_thenCreatesCustomer() {
        CustomersDTO customerDTO = new CustomersDTO("João Alfredo", "01234567890");

        HttpEntity<CustomersDTO> body = new HttpEntity<>(customerDTO);

        ResponseEntity<CustomersModel> response = restTemplate.exchange("/customers", HttpMethod.POST, body, CustomersModel.class);

        assertNotNull(response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}
