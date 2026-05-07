package org.acme.dto;

public record ProdutoDTO(
        String name,
        Double price,
        Integer quantidadeEstoque,
        String fornecedor) {
}
