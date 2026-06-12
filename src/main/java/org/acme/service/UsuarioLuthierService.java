package org.acme.service;

import org.acme.dto.UsuarioLuthierDTO;
import org.acme.dto.UsuarioLuthierResponseDTO;
import java.util.List;

public interface UsuarioLuthierService {
    UsuarioLuthierResponseDTO create(UsuarioLuthierDTO dto);
    UsuarioLuthierResponseDTO update(long id, UsuarioLuthierDTO dto);
    void delete(long id);
    UsuarioLuthierResponseDTO findById(long id);
    UsuarioLuthierResponseDTO findByUsername(String username);
    List<UsuarioLuthierResponseDTO> findAll();
}
