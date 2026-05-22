package org.acme.dto;

import org.acme.model.Perfil;
import org.acme.model.Usuario;

public record UsuarioResponseDTO(
    Long id,
    String nome,
    String username,
    String email,
    Perfil perfil
) {
    public static UsuarioResponseDTO valueOf(Usuario usuario) {
        return new UsuarioResponseDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getUsername(),
            usuario.getEmail(),
            usuario.getPerfil()
        );
    }
}

