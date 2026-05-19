package org.acme.service;

import java.util.List;
import org.acme.dto.UsuarioDTO;
import org.acme.dto.UsuarioResponseDTO;

public interface UsuarioService {
    UsuarioResponseDTO create(UsuarioDTO dto);
    UsuarioResponseDTO update(Long id, UsuarioDTO dto);
    void delete(Long id);
    UsuarioResponseDTO findById(Long id);
    UsuarioResponseDTO findByUsername(String username);
    UsuarioResponseDTO findByLoginAndSenha(String login, String senha);
    List<UsuarioResponseDTO> findAll();
}
