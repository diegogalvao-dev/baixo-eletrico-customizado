package org.acme.dto;

import java.util.List;

public record PessoaClienteDTO(
    String name,
    String email,
    String cpf,
    List<Long> pedidos,
    List<Long> baixoCustomizados
) { }