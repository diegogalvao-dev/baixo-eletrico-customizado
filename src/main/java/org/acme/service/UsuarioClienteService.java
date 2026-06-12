package org.acme.service;

import org.acme.dto.UsuarioClienteDTO;
import org.acme.dto.UsuarioClienteResponseDTO;
import java.util.List;

public interface UsuarioClienteService {
    UsuarioClienteResponseDTO create(UsuarioClienteDTO dto);
    UsuarioClienteResponseDTO update(long id, UsuarioClienteDTO dto);
    void delete(long id);
    UsuarioClienteResponseDTO findById(long id);
    UsuarioClienteResponseDTO findByUsername(String username);
    List<UsuarioClienteResponseDTO> findAll();
}
