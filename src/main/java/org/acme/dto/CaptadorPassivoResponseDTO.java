package org.acme.dto;

import org.acme.model.CaptadorPassivo;
import org.acme.model.CaptadorPosicao;

public record CaptadorPassivoResponseDTO(

    Long id,
    String marca,
    Double price,
    CaptadorPosicao captadorPosicao,
    Double resistencia,
    Integer numeroBobinas

) {

    public static CaptadorPassivoResponseDTO valueOf(CaptadorPassivo captadorPassivo){

        if(captadorPassivo == null){
            return null;
        }

        return new CaptadorPassivoResponseDTO(
            captadorPassivo.getId(),
            captadorPassivo.getMarca(),
            captadorPassivo.getPrice(),
            captadorPassivo.getCaptadorPosicao(),
            captadorPassivo.getResistencia(),
            captadorPassivo.getNumeroBobinas());
    }

}

