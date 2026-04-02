package org.acme.dto;

import org.acme.model.CaptadorPosicao;

public record CaptadorPassivoDTO(

    String marca,
    Double price,
    CaptadorPosicao captadorPosicao,
    Double resistencia,
    Integer numeroBobinas

) { } 

