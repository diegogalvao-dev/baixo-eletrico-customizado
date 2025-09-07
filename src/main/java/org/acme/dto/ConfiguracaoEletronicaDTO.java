package org.acme.dto;

import org.acme.model.BaixoCustomizado;

public record ConfiguracaoEletronicaDTO(Integer volumeKnobs, Integer toneKnobs, Boolean circuitoAtivo, BaixoCustomizado baixoCustomizado) {
}
