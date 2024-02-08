package com.boardcamp.api.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boardcamp.api.dtos.RentalsDTO;
import com.boardcamp.api.models.RentalsModel;
import com.boardcamp.api.services.RentalsService;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/rentals")
public class RentalsController {
    final RentalsService rentalsService;

    RentalsController(RentalsService rentalsService) {
        this.rentalsService = rentalsService;
    }

    @PostMapping
    public ResponseEntity<RentalsModel> createRental(@RequestBody @Valid RentalsDTO body) {
        Optional<RentalsModel> rental = rentalsService.save(body);

        if (!rental.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(rental.get());
    }
}
