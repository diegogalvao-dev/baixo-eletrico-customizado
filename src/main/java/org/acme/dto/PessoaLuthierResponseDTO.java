package org.acme.dto;

import java.util.List;

import org.acme.model.PessoaLuthier;

public record PessoaLuthierResponseDTO(
    Long id,
    String name,
    String email,
    String cnpj,
    List<Long> baixoCustomizadosIds
) {
    
    public static PessoaLuthierResponseDTO valueOf(PessoaLuthier pessoaLuthier) {
        if (pessoaLuthier == null) {
            return null;
        }
        return new PessoaLuthierResponseDTO(
            pessoaLuthier.getId(),
            pessoaLuthier.getName(),
            pessoaLuthier.getEmail(),
            pessoaLuthier.getCnpj(),
            pessoaLuthier.getBaixoCustomizados().stream().map(b -> b.getId()).toList()
        );

    }

}
