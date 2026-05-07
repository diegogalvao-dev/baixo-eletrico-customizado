package org.acme.dto;

import org.acme.model.BaixoCor;
import org.acme.model.BaixoModeloBase;
import org.acme.model.Fornecedor;

public record BaixoDTO(

    BaixoModeloBase baixoModeloBase,
    Integer numeroCordas,
    BaixoCor baixoCor,
    String name,
    Double price,
    Integer quantidadeEstoque,
    String fornecedor

) { }  