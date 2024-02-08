package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RentalsDTO {
    @NotBlank
    @NotNull
    private Long customerId;

    @NotBlank
    @NotNull
    private Long gameId;

    private String rentDate;

    private Integer daysRented;

    private String returnDate;

    private Long originalPrice;

    private Integer delayFee;

    private String[] customer;

    private String[] game;
}
