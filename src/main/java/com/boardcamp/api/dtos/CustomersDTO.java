package com.boardcamp.api.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomersDTO {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @Size(min = 11, max = 11)
    private String cpf;

}
