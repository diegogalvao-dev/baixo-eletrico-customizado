package org.acme.dto;

import java.util.List;

import org.acme.model.PessoaCliente;

public record PessoaClienteResponseDTO(
    Long id,
    String name,
    String email,
    String cpf,
    List<Long> pedidos,
    List<Long> baixoCustomizados
) {

    public static PessoaClienteResponseDTO valueOf(PessoaCliente pessoaCliente){

    if (pessoaCliente == null){
        return null;
    }

        return new PessoaClienteResponseDTO(
            pessoaCliente.getId(),
            pessoaCliente.getName(),
            pessoaCliente.getEmail(),
            pessoaCliente.getCpf(),
            pessoaCliente.getPedidos().stream().map(c -> c.getId()).toList(),
            pessoaCliente.getBaixoCustomizados().stream().map(b -> b.getId()).toList()
        );

    }

}