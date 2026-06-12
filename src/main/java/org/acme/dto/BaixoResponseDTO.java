package org.acme.dto;

import org.acme.model.Baixo;
import org.acme.model.BaixoCor;
import org.acme.model.BaixoModeloBase;

import java.util.List;

public record BaixoResponseDTO(

        Long id,
        BaixoModeloBase baixoModeloBase,
        Integer numeroCordas,
        BaixoCor baixoCor,
        String name,
        Double price,
        Integer quantidadeEstoque,
        String fornecedor,
        List<String> nomeImagens,
        String imagemPrincipal

) {

    public static BaixoResponseDTO valueOf(Baixo baixo) {

        if (baixo == null) {
            return null;
        }

        return new BaixoResponseDTO(
                baixo.getId(),
                baixo.getBaixoModeloBase(),
                baixo.getNumeroDeCordas(),
                baixo.getBaixoCor(),
                baixo.getName(),
                baixo.getPrice(),
                baixo.getQuantidadeEstoque(),
                baixo.getFornecedor() != null ? baixo.getFornecedor().getName() : null,
                baixo.getNomeImagens() != null ? List.copyOf(baixo.getNomeImagens()) : List.of(),
                baixo.getImagemPrincipal());
    }

}
