package org.acme.dto;

import org.acme.model.BaixoCustomizado;
import org.acme.model.TipoCaptador;

public record CaptadoresDTO(TipoCaptador tipoCaptador, String marcaCaptador, String posicao) {
}
