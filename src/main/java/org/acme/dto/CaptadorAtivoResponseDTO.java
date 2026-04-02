package org.acme.dto;

import org.acme.model.CaptadorAtivo;
import org.acme.model.CaptadorPosicao;

public record CaptadorAtivoResponseDTO(
    Long id,
    String marca,
    Double price,
    CaptadorPosicao captadorPosicao,
    Boolean possuiBateria,
    Boolean possuiAmplificador
) { 

    public static CaptadorAtivoResponseDTO valueOf(CaptadorAtivo captadorAtivo){

        if(captadorAtivo == null){
            return null;
        }

        return new CaptadorAtivoResponseDTO(
            captadorAtivo.getId(),
            captadorAtivo.getMarca(),
            captadorAtivo.getPrice(),
            captadorAtivo.getCaptadorPosicao(),
            captadorAtivo.getPossuiBateria(),
            captadorAtivo.getPossuiAmplificador());
}

} 