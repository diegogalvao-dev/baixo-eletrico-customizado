package org.acme.dto;

import org.acme.model.CorBaixo;
import org.acme.model.ModeloBaseBaixo;

import java.util.List;

/**
 * DTO para criar ou atualizar um BaixoCustomizado.
 * Este record agora inclui um objeto aninhado para a configuração eletrônica,
 * refletindo a relação de composição.
 */
public record BaixoCustomizadoDTO(
        ModeloBaseBaixo modeloBaseBaixo,
        CorBaixo corBaixo,
        Double priceEstimated,

        // Em vez de um ID, agora recebemos um objeto DTO completo para criar a ConfiguracaoEletronica.
        ConfiguracaoEletronicaDTO configuracaoEletronica,

        List<Long> captadoresListIds,
        List<Long> acessoriosListIds) {
}
