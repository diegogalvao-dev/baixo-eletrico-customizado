package org.acme.dto;

import org.acme.model.Produto;

public record ProdutoResponseDTO(
        Long id,
        String name,
        Double price,
        Integer quantidadeEstoque,
        String fornecedor) {

    public static ProdutoResponseDTO valueOf(Produto produto) {
        if (produto == null) {
            return null;
        }

        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getName(),
                produto.getPrice(),
                produto.getQuantidadeEstoque(),
                produto.getFornecedor() != null ? produto.getFornecedor().getName() : null);
    }
}
