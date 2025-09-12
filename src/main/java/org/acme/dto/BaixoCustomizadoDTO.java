package org.acme.dto;

import org.acme.model.CorBaixo;
import org.acme.model.ModeloBaseBaixo;

import java.util.List;

public record BaixoCustomizadoDTO(
        ModeloBaseBaixo modeloBaseBaixo,
        CorBaixo corBaixo,
        Double priceEstimated,
//        Long configuracaoEletronica,
        List<Long> captadoresListIds,
        List<Long> acessoriosListIds) {
}
