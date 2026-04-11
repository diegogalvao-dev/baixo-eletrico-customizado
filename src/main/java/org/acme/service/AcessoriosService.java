package org.acme.service;

import org.acme.dto.AcessorioResponseDTO;
import org.acme.dto.AcessoriosDTO;

import java.util.List;

public interface AcessoriosService {

    AcessorioResponseDTO create(AcessoriosDTO acessoriosDTO);
    void update(long id, AcessoriosDTO acessoriosDTO);
    void delete(long id);

    List<AcessorioResponseDTO> findAll(Integer page, Integer pageSize);
    List<AcessorioResponseDTO> search(String term, Integer page, Integer pageSize);
    long count();
    AcessorioResponseDTO findById(long id);

}
