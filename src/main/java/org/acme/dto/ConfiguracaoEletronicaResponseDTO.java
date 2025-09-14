package org.acme.dto;

import org.acme.model.BaixoCustomizado;
import org.acme.model.ConfiguracaoEletronica;

public record ConfiguracaoEletronicaResponseDTO(Long id, Integer volumeKnobs, Integer toneKnobs, Boolean circuitoAtivo) {

    public static ConfiguracaoEletronicaResponseDTO valueOf(ConfiguracaoEletronica configuracaoEletronica){

        if(configuracaoEletronica == null){
            return null;
        }

        return new ConfiguracaoEletronicaResponseDTO(
                configuracaoEletronica.getId(),
                configuracaoEletronica.getVolumeKnobs(),
                configuracaoEletronica.getToneKnobs(),
                configuracaoEletronica.getCircuitoAtivo());
    }

}
