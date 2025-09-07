package org.acme.service;

import org.acme.dto.BaixoCustomizadoDTO;
import org.acme.dto.BaixoCustomizadoResponseDTO;

import java.util.List;

public interface BaixoCustomizadoService {

    BaixoCustomizadoResponseDTO create(BaixoCustomizadoDTO baixoCustomizadoDTO);
    void update(long id, BaixoCustomizadoDTO baixoCustomizadoDTO);
    void delete(long id);

    List<BaixoCustomizadoResponseDTO> findAll();

}
