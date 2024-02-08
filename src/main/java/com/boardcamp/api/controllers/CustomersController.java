package com.boardcamp.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dtos.CustomersDTO;
import com.boardcamp.api.models.CustomersModel;
import com.boardcamp.api.services.CustomersService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@CrossOrigin("*")
@RestController
@RequestMapping("/customers")
public class CustomersController {
    final CustomersService customersService;

    CustomersController(CustomersService customersService) {
        this.customersService = customersService;
    }

    @PostMapping
    public ResponseEntity<CustomersModel> createCustomer(@RequestBody @Valid CustomersDTO body) {
        if(customersService.existsByCpf(body.getCpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        CustomersModel customer = customersService.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }
    
}
