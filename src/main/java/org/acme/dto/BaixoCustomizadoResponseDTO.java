package org.acme.dto;

import org.acme.model.BaixoCor;
import org.acme.model.BaixoCustomizado;
import org.acme.model.BaixoModeloBase;
import org.acme.model.BaixoStatus;
import org.acme.model.Captador;

import java.util.List;

public record BaixoCustomizadoResponseDTO(
        Long id,
        String name,
        Double price,
        BaixoModeloBase baixoModeloBase,
        String description,
        BaixoCor baixoCor,
        Long configuracaoEletronica,
        List<Long> captadorList,
        BaixoStatus baixoStatus,
        Long usuarioLuthierId,
        List<String> nomeImagens) {

    public static BaixoCustomizadoResponseDTO valueOf(BaixoCustomizado baixoCustomizado) {

        if (baixoCustomizado == null) {
            return null;
        }

        return new BaixoCustomizadoResponseDTO(
                baixoCustomizado.getId(),
                baixoCustomizado.getName(),
                baixoCustomizado.getPrice(),
                baixoCustomizado.getBaixoModeloBase(),
                baixoCustomizado.getDescription(),
                baixoCustomizado.getBaixoCor(),
                baixoCustomizado.getConfiguracaoEletronica() != null ? baixoCustomizado.getConfiguracaoEletronica().getId() : null,
                baixoCustomizado.getCaptador() != null ? baixoCustomizado.getCaptador().stream().map(Captador::getId).toList() : List.of(),
                baixoCustomizado.getBaixoStatus(),
                baixoCustomizado.getUsuarioLuthier() != null ? baixoCustomizado.getUsuarioLuthier().getId() : null,
                baixoCustomizado.getNomeImagens() != null ? List.copyOf(baixoCustomizado.getNomeImagens()) : List.of());
    }
}
