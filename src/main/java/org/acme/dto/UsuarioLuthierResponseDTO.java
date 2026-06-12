package org.acme.dto;

import org.acme.model.UsuarioLuthier;

public record UsuarioLuthierResponseDTO(
        Long id,
        String nome,
        String username,
        String email,
        String fotoPerfil,
        Long perfilId,
        String perfilNome,
        String cnpj,
        String especialidade
) {
    public static UsuarioLuthierResponseDTO valueOf(UsuarioLuthier u) {
        if (u == null) return null;
        return new UsuarioLuthierResponseDTO(
                u.getId(),
                u.getNome(),
                u.getUsername(),
                u.getEmail(),
                u.getFotoPerfil(),
                u.getPerfil() != null ? (long) u.getPerfil().getId() : null,
                u.getPerfil() != null ? u.getPerfil().getNome() : null,
                u.getCnpj(),
                u.getEspecialidade()
        );
    }
}
