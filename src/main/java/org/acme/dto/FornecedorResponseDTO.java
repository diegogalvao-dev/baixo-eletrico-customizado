package org.acme.dto;

import java.util.List;

import org.acme.model.Fornecedor;

public record FornecedorResponseDTO(
    Long id,
    String name,
    String cnpj,
    List<Long> produtos) {

    public static FornecedorResponseDTO valueOf(Fornecedor fornecedor){

        if (fornecedor == null){
            return null;
        }

        return new FornecedorResponseDTO(
            fornecedor.getId(),
            fornecedor.getName(),
            fornecedor.getCnpj(),
            fornecedor.getProduto().stream().map(p -> p.getId()).toList());
    }


    

}