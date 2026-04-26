package br.com.cliente.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ClienteRequest(

        @NotBlank
        @Size(max = 120)
        String nome,

        @NotNull
        Integer idade,

        @NotBlank
        @Size(min = 11, max = 14)
        String cpf
) {}
