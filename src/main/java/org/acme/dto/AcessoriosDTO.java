package org.acme.dto;

import org.acme.model.AcessorioTipo;

public record AcessoriosDTO(
        
        AcessorioTipo acessorioTipo,
        String material,
        Double tamanho,
        String name,
        Double price,
        Integer quantidadeEstoque,
        Long fornecedor) {
}
