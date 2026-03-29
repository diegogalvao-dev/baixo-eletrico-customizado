package org.acme.dto;

import org.acme.model.BaixoCor;

public record BaixoDTO(

    Long baixoModeloBaixoId,
    Integer numeroCordas,
    BaixoCor baixoCor,
    String name,
    Double price,
    Integer quantidadeEstoque,
    Long fornecedorId

) { } 