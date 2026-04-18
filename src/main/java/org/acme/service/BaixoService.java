package org.acme.service;

import java.util.List;

import org.acme.dto.BaixoDTO;
import org.acme.dto.BaixoResponseDTO;

public interface BaixoService {

    BaixoResponseDTO create(BaixoDTO baixoDTO);
    void update(long id, BaixoDTO baixoDTO);
    void delete(long id);

    List<BaixoResponseDTO> findAll(Integer page, Integer pageSize);
    List<BaixoResponseDTO> search(String term, Integer page, Integer pageSize);
    long count();
    BaixoResponseDTO findById(long id);

}