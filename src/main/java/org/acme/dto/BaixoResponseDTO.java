package org.acme.dto;

import org.acme.model.Baixo;
import org.acme.model.BaixoCor;
import org.acme.model.BaixoModeloBase;

public record BaixoResponseDTO(

    Long id,
    BaixoModeloBase baixoModeloBaixo,
    Integer numeroCordas,
    BaixoCor baixoCor,
    String name,
    Double price,
    Integer quantidadeEstoque,
    Long fornecedorId

) {

    public static BaixoResponseDTO valueOf(Baixo baixo){

        if(baixo == null){
            return null;
        }

        return new BaixoResponseDTO(
            baixo.getId(),
            baixo.getBaixoModeloBase(),
            baixo.getNumeroDeCordas(),
            baixo.getBaixoCor(),
            baixo.getName(),
            baixo.getPrice(),
            baixo.getQuantidadeEstoque(),
            baixo.getFornecedor().getId());
    }

} 
