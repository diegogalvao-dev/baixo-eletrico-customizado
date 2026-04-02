package org.acme.service;

import java.util.List;

import org.acme.dto.BaixoCustomizadoDTO;
import org.acme.dto.BaixoCustomizadoResponseDTO;

public interface BaixoCustomizadoService {

    BaixoCustomizadoResponseDTO create(BaixoCustomizadoDTO baixoCustomizadoDTO);
    void update(long id, BaixoCustomizadoDTO baixoCustomizadoDTO);
    void delete(long id);

    List<BaixoCustomizadoResponseDTO> findAll();

}