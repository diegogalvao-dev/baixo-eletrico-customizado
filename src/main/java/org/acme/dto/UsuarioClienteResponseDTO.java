package org.acme.dto;

import org.acme.model.UsuarioCliente;

public record UsuarioClienteResponseDTO(
        Long id,
        String nome,
        String username,
        String email,
        String fotoPerfil,
        Long perfilId,
        String perfilNome,
        String cpf
) {
    public static UsuarioClienteResponseDTO valueOf(UsuarioCliente u) {
        if (u == null) return null;
        return new UsuarioClienteResponseDTO(
                u.getId(),
                u.getNome(),
                u.getUsername(),
                u.getEmail(),
                u.getFotoPerfil(),
                u.getPerfil() != null ? (long) u.getPerfil().getId() : null,
                u.getPerfil() != null ? u.getPerfil().getNome() : null,
                u.getCpf()
        );
    }
}
