package com.boardcamp.api.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CustomersDTO {
    @NotNull
    private String name;

    @NotNull
    @Min(value = 10000000000L, message = "CPF must be at least 11 digits")
    @Max(value = 99999999999L, message = "CPF must be at most 11 digits")
    private Long cpf;

}
