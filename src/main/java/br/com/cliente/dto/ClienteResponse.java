package br.com.cliente.dto;

public record ClienteResponse(
        Long id,
        String nome,
        Integer idade,
        String cpf
) {}
