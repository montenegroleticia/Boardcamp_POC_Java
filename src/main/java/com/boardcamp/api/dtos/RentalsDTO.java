package com.boardcamp.api.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RentalsDTO {
    @NotNull
    private Long customerId;

    @NotNull
    private Long gameId;

    private LocalDate rentDate;

    private Integer daysRented;

    private String returnDate;

    private Long originalPrice;

    private Integer delayFee;
}
