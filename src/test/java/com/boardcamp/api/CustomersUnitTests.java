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
import com.boardcamp.api.models.CustomersModel;
import com.boardcamp.api.repositories.CustomersRepository;
import com.boardcamp.api.services.CustomersService;

@SpringBootTest
class CustomersUnitTests {

    @InjectMocks
    private CustomersService customersService;

    @Mock
    private CustomersRepository customersRepository;

    @Test
    void givenCustomer_whenCreatingCustomer_thenCreateCustomer() {
        CustomersDTO customersDTO = new CustomersDTO("João Alfredo", "01234567890");
        CustomersModel newCustomer = new CustomersModel(customersDTO);

        doReturn(false).when(customersRepository).existsByCpf(any());
        doReturn(newCustomer).when(customersRepository).save(any());

        CustomersModel result = customersService.save(customersDTO);

        assertNotNull(result);
        assertEquals(newCustomer, result);
    }
}
