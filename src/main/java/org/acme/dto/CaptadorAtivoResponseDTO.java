package org.acme.dto;

import org.acme.model.CaptadorAtivo;

public record CaptadorAtivoResponseDTO(
    Long id,
    Boolean possuiBateria,
    Boolean possuiAmplificador
) { 

    public static CaptadorAtivoResponseDTO valueOf(CaptadorAtivo captadorAtivo){

        if(captadorAtivo == null){
            return null;
        }

        return new CaptadorAtivoResponseDTO(
            captadorAtivo.getId(),
            captadorAtivo.getPossuiBateria(),
            captadorAtivo.getPossuiAmplificador());
}

} 