package org.acme.dto;

import org.acme.model.Acessorios;
import org.acme.model.BaixoCustomizado;
import org.acme.model.TipoAcessorio;

public record AcessorioResponseDTO(Long id, TipoAcessorio tipoAcessorio, String marcaAcessorios, String material, BaixoCustomizado baixoCustomizadoAce) {

    public static AcessorioResponseDTO valueOf(Acessorios acessorios){

        if (acessorios == null){
            return null;
        }

        return new AcessorioResponseDTO(acessorios.getId(), acessorios.getTipoAcessorio(), acessorios.getMarcaAcessorio(), acessorios.getMaterial(), acessorios.getBaixoCustomizadoAce());

    }

}
