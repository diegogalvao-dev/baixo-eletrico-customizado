package org.acme.dto;

import org.acme.model.BaixoCor;
import org.acme.model.BaixoCustomizado;
import org.acme.model.BaixoModeloBase;
import org.acme.model.BaixoStatus;
import org.acme.model.ConfiguracaoEletronica;

import java.util.List;

public record BaixoCustomizadoResponseDTO(
        Long id,
        BaixoModeloBase baixoModeloBase,
        String description,
        BaixoCor baixoCor,
        ConfiguracaoEletronica configuracaoEletronica,
        List<Long> captadorList,
        Double estimatedPrice,
        BaixoStatus baixoStatus,
        Long pessoaCliente,
        Long pessoaLuthier
) {

    public static BaixoCustomizadoResponseDTO valueOf(BaixoCustomizado baixoCustomizado){

        if (baixoCustomizado == null){
            return null;
        }

        return new BaixoCustomizadoResponseDTO(
                baixoCustomizado.getId(),
                baixoCustomizado.getBaixoModeloBase(),
                baixoCustomizado.getDescription(),
                baixoCustomizado.getBaixoCor(),
                baixoCustomizado.getConfiguracaoEletronica(),
                baixoCustomizado.getCaptador().stream().map(c -> c.getId()).toList(),
                baixoCustomizado.getEstimatedPrice(),
                baixoCustomizado.getBaixoStatus(),
                baixoCustomizado.getPessoaCliente().getId(),
                baixoCustomizado.getPessoaLuthier().getId()
        );

    }

}
