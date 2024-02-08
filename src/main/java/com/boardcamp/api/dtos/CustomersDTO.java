package com.boardcamp.api.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomersDTO {
    @NotBlank
    private String name;

    @NotBlank
    @Size(max = 11, min = 11)
    private Long cpf;
}
