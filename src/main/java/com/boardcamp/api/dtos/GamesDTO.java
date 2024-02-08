package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GamesDTO {

    @NotBlank
    private String name;

    private String image;

    private Integer stockTotal;

    private Float pricePerDay;
}
