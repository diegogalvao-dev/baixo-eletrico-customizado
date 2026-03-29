package org.acme.dto;

import java.util.List;

public record PessoaLuthierDTO(
    String name,
    String email,
    String cnpj,
    List<Long> baixoCustomizados

) { } 