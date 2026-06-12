package org.acme.dto;

public record ItemCarrinhoDTO(
    Long produtoId,
    Integer quantidade,
    Double precoUnitario
) { }
