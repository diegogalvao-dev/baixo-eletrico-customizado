package org.acme.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthUsuarioDTO (
    @NotBlank(message = "O login é obrigatório")
    String login,

    @NotBlank(message = "A senha é obrigatória")
    String senha
) {
}
