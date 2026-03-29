package org.acme.dto;

import org.acme.model.CaptadorPassivo;

public record CaptadorPassivoResponseDTO(

    Long id,
    Double resistencia,
    Integer numeroBobinas

) {

    public static CaptadorPassivoResponseDTO valueOf(CaptadorPassivo captadorPassivo){

        if(captadorPassivo == null){
            return null;
        }

        return new CaptadorPassivoResponseDTO(
            captadorPassivo.getId(),
            captadorPassivo.getResistencia(),
            captadorPassivo.getNumeroBobinas());
    }

}

