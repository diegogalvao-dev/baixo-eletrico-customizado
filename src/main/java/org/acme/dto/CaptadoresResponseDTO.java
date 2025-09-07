package org.acme.dto;

import org.acme.model.BaixoCustomizado;
import org.acme.model.Captadores;
import org.acme.model.TipoCaptador;

public record CaptadoresResponseDTO(Long id, TipoCaptador tipoCaptador, String marcaCaptador, String posicao, BaixoCustomizado baixoCustomizadoCap) {

    public static CaptadoresResponseDTO valueOf(Captadores captadores){

        if (captadores == null){
            return null;
        }

        return new CaptadoresResponseDTO(captadores.getId(), captadores.getTipoCaptador(), captadores.getMarcaCaptador(), captadores.getPosicao(), captadores.getBaixoCustomizadoCap());

    }

}
