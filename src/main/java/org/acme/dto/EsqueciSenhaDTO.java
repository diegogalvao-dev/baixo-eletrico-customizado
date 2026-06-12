package org.acme.dto;

import jakarta.validation.constraints.NotBlank;

public record EsqueciSenhaDTO(
    @NotBlank(message = "O username é obrigatório")
    String username
) {
}
