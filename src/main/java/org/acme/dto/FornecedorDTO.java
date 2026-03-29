package org.acme.dto;

import java.util.List;

public record FornecedorDTO(
        String name,
        String cnpj,
        List<Long> produtos) {
}
