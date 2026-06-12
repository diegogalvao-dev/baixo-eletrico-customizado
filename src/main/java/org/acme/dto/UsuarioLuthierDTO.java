package org.acme.dto;

public record UsuarioLuthierDTO(
        String nome,
        String email,
        String cnpj,
        String especialidade
) {}
