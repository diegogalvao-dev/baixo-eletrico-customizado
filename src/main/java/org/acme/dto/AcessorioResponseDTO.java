package org.acme.dto;

import org.acme.model.Acessorio;
import org.acme.model.AcessorioTipo;

import java.util.List;

public record AcessorioResponseDTO(
        Long id,
        AcessorioTipo acessorioTipo,
        String material,
        Double tamanho,
        String name,
        Double price,
        Integer quantidadeEstoque,
        String fornecedor,
        List<String> nomeImagens,
        String imagemPrincipal) {

    public static AcessorioResponseDTO valueOf(Acessorio acessorio) {

        if (acessorio == null) {
            return null;
        }

        return new AcessorioResponseDTO(
                acessorio.getId(),
                acessorio.getAcessorioTipo(),
                acessorio.getMaterial(),
                acessorio.getTamanho(),
                acessorio.getName(),
                acessorio.getPrice(),
                acessorio.getQuantidadeEstoque(),
                acessorio.getFornecedor().getName(),
                acessorio.getNomeImagens() != null ? List.copyOf(acessorio.getNomeImagens()) : List.of(),
                acessorio.getImagemPrincipal());

    }

}
