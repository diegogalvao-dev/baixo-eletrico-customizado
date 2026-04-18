package org.acme.dto;

import org.acme.model.BaixoCor;
import org.acme.model.BaixoModeloBase;

public record BaixoDTO(

    BaixoModeloBase baixoModeloBase,
    Integer numeroCordas,
    BaixoCor baixoCor,
    String name,
    Double price,
    Integer quantidadeEstoque,
    Long fornecedor

) { } 