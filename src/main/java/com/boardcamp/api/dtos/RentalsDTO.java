package com.boardcamp.api.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RentalsDTO {
    public RentalsDTO() {
    }

    public RentalsDTO(int customerId, int gameId, int daysRented) {
        this.customerId = (long) customerId;
        this.gameId = (long) gameId;
        this.daysRented = daysRented;
    }

    public RentalsDTO(long customerId, long gameId, int daysRented) {
        this.customerId = customerId;
        this.gameId = gameId;
        this.daysRented = daysRented;
    }

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
