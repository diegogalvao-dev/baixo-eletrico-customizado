package org.acme.service;

import java.util.List;

import org.acme.dto.BaixoCustomizadoDTO;
import org.acme.dto.BaixoCustomizadoResponseDTO;

public interface BaixoCustomizadoService {

    BaixoCustomizadoResponseDTO create(BaixoCustomizadoDTO baixoCustomizadoDTO);
    BaixoCustomizadoResponseDTO update(long id, BaixoCustomizadoDTO baixoCustomizadoDTO);
    void delete(long id);

    List<BaixoCustomizadoResponseDTO> findAll(Integer page, Integer pageSize);
    List<BaixoCustomizadoResponseDTO> search(String term, Integer page, Integer pageSize);
    long count();
    BaixoCustomizadoResponseDTO findById(long id);
    List<BaixoCustomizadoResponseDTO> getMyProjetos(String username);
}