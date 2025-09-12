package org.acme.dto;

import org.acme.model.BaixoCustomizado;
import org.acme.model.CorBaixo;
import org.acme.model.ModeloBaseBaixo;

import java.util.List;

public record BaixoCustomizadoResponseDTO(
        Long id,
        ModeloBaseBaixo modeloBaseBaixo,
        CorBaixo corBaixo,
        Double priceEstimated,
//        Long configuracaoEletronica,
        List<Long> capitadoresListIds,
        List<Long> acessoriosListIds) {

    public static BaixoCustomizadoResponseDTO valueOf(BaixoCustomizado baixoCustomizado){

        if (baixoCustomizado == null){
            return null;
        }

        return new BaixoCustomizadoResponseDTO(
                baixoCustomizado.getId(),
                baixoCustomizado.getModeloBaseBaixo(),
                baixoCustomizado.getCorBaixo(),
                baixoCustomizado.getPriceEstimated(),
//                baixoCustomizado.getConfiguracaoEletronica().getId(),
                baixoCustomizado.getCaptadoresList().stream().map(c -> c.getId()).toList(),
                baixoCustomizado.getAcessoriosList().stream().map(a -> a.getId()).toList()
        );

    }

}
