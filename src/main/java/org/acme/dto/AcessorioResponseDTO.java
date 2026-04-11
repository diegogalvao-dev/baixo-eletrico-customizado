package org.acme.dto;

import org.acme.model.Acessorio;
import org.acme.model.AcessorioTipo;

public record AcessorioResponseDTO(
        Long id,
        AcessorioTipo acessorioTipo,
        String material,
        Double tamanho,
        String name,
        Double price,
        Integer quantidadeEstoque,
        Long fornecedor) {

        public static AcessorioResponseDTO valueOf(Acessorio acessorio){

            if (acessorio == null){
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
                acessorio.getFornecedor().getId());

    }

}
