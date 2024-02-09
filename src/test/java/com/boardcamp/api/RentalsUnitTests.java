package com.boardcamp.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.boardcamp.api.dtos.CustomersDTO;
import com.boardcamp.api.dtos.GamesDTO;
import com.boardcamp.api.dtos.RentalsDTO;
import com.boardcamp.api.models.CustomersModel;
import com.boardcamp.api.models.GamesModel;
import com.boardcamp.api.models.RentalsModel;
import com.boardcamp.api.repositories.CustomersRepository;
import com.boardcamp.api.repositories.GamesRepository;
import com.boardcamp.api.repositories.RentalsRepository;
import com.boardcamp.api.services.RentalsService;

@SpringBootTest
class RentalsUnitTests {

    @InjectMocks
    private RentalsService rentalsService;

    @Mock
    private RentalsRepository rentalsRepository;

    @Mock
    private GamesRepository gamesRepository;

    @Mock
    private CustomersRepository customersRepository;

    @Test
    void givenRental_whenCreatingRental_thenCreatesRental() {
        RentalsDTO rentalsDTO = new RentalsDTO(1, 1, 3);

        GamesModel game = new GamesModel(
                new GamesDTO("Banco Imobiliário", "http://www.imagem.com.br/banco_imobiliario.jpg", 3, 1000));
        CustomersModel customer = new CustomersModel(new CustomersDTO("João Alfredo", "01234567890"));

        RentalsModel newRental = new RentalsModel(rentalsDTO, game, customer);

        doReturn(false).when(rentalsRepository).existsById(any());
        doReturn(game).when(gamesRepository).findById(1L);
        doReturn(customer).when(customersRepository).findById(1L);
        doReturn(newRental).when(rentalsRepository).save(any());

        RentalsModel result = rentalsService.save(rentalsDTO);

        assertNotNull(result);
        assertEquals(newRental, result);
    }
}
