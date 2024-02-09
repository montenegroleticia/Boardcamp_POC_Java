package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GamesDTO {

    public GamesDTO(String name, String image, Integer stockTotal, Integer pricePerDay) {
        this.name = name;
        this.image = image;
        this.stockTotal = stockTotal;
        this.pricePerDay = pricePerDay;
    }

    @NotBlank
    private String name;

    private String image;

    private Integer stockTotal;

    private Integer pricePerDay;
}
