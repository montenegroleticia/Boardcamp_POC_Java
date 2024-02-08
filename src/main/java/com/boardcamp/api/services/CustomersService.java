package com.boardcamp.api.services;

import org.springframework.stereotype.Service;

import com.boardcamp.api.dtos.CustomersDTO;
import com.boardcamp.api.models.CustomersModel;
import com.boardcamp.api.repositories.CustomersRepository;

@Service
public class CustomersService {
    final CustomersRepository customersRepository;

    CustomersService(CustomersRepository customersRepository) {
        this.customersRepository = customersRepository;
    }

    public CustomersModel save(CustomersDTO dto) {
        CustomersModel customer = new CustomersModel(dto);
        return customersRepository.save(customer);
    }

    public boolean existsByCpf(Long cpf) {
        return customersRepository.existsByCpf(cpf);
    }
}
