package org.acme.dto;

import org.acme.model.Produto;

public record ProdutoResponseDTO(
        Long id,
        String name,
        Double price,
        Integer quantidadeEstoque,
        String fornecedor,
        String imagemPrincipal,
        String type,
        String baixoModeloBase,
        Integer numeroCordas,
        String baixoCor,
        String acessorioTipo,
        String material,
        Double tamanho,
        java.util.List<String> nomeImagens) {

    public static ProdutoResponseDTO valueOf(Produto produto) {
        if (produto == null) {
            return null;
        }

        String type = null;
        String baixoModeloBase = null;
        Integer numeroCordas = null;
        String baixoCor = null;
        String acessorioTipo = null;
        String material = null;
        Double tamanho = null;

        if (produto instanceof org.acme.model.Baixo b) {
            type = "BAIXO";
            baixoModeloBase = b.getBaixoModeloBase() != null ? b.getBaixoModeloBase().getNAME() : null;
            numeroCordas = b.getNumeroDeCordas();
            baixoCor = b.getBaixoCor() != null ? b.getBaixoCor().getNAME() : null;
        } else if (produto instanceof org.acme.model.Acessorio a) {
            type = "ACESSORIO";
            acessorioTipo = a.getAcessorioTipo() != null ? a.getAcessorioTipo().getNAME() : null;
            material = a.getMaterial();
            tamanho = a.getTamanho();
        }

        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getName(),
                produto.getPrice(),
                produto.getQuantidadeEstoque(),
                produto.getFornecedor() != null ? produto.getFornecedor().getName() : null,
                produto.getImagemPrincipal(),
                type,
                baixoModeloBase,
                numeroCordas,
                baixoCor,
                acessorioTipo,
                material,
                tamanho,
                produto.getNomeImagens() != null ? java.util.List.copyOf(produto.getNomeImagens()) : java.util.List.of());
    }
}
