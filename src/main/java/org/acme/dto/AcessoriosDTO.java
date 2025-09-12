package org.acme.dto;

import org.acme.model.TipoAcessorio;

public record AcessoriosDTO(TipoAcessorio tipoAcessorio, String marcaAcessorios, String material) {
}
