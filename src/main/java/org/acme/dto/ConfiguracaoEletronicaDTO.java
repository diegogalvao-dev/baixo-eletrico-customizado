package org.acme.dto;

public record ConfiguracaoEletronicaDTO(
        Integer volumeKnobs,
        Integer toneKnobs,
        Boolean circuitoAtivo
) { }
