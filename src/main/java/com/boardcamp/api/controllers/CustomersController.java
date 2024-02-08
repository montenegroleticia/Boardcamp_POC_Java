package com.boardcamp.api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping
    public ResponseEntity<List<CustomersModel>> getAllCustomers() {
        List<CustomersModel> cutomers = customersService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(cutomers);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CustomersModel> getCustomersById(@PathVariable Long id) {
        CustomersModel cutomer = customersService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(cutomer);
    }
}
