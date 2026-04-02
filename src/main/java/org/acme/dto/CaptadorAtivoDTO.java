package org.acme.dto;

import org.acme.model.CaptadorPosicao;

public record CaptadorAtivoDTO(
    String marca,
    Double price,
    CaptadorPosicao captadorPosicao,
    Boolean possuiBateria,
    Boolean possuiAmplificador

) { } 