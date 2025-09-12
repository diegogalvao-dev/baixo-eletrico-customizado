package org.acme.dto;

import org.acme.model.Acessorios;
import org.acme.model.TipoAcessorio;

public record AcessorioResponseDTO(Long id, TipoAcessorio tipoAcessorio, String marcaAcessorios, String material) {

    public static AcessorioResponseDTO valueOf(Acessorios acessorios){

        if (acessorios == null){
            return null;
        }

        return new AcessorioResponseDTO(acessorios.getId(), acessorios.getTipoAcessorio(), acessorios.getMarcaAcessorios(), acessorios.getMaterial());

    }

}
