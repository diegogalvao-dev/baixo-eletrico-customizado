package org.acme.service;

import org.acme.dto.ProdutoDTO;
import org.acme.dto.ProdutoResponseDTO;

import java.util.List;

public interface ProdutoService {

    void update(long id, ProdutoDTO produtoDTO);

    void delete(long id);

    List<ProdutoResponseDTO> findAll(Integer page, Integer pageSize);

    List<ProdutoResponseDTO> search(String term, Integer page, Integer pageSize);

    long count();

    ProdutoResponseDTO findById(long id);

    List<ProdutoResponseDTO> searchByPriceRange(Double min, Double max, Integer page, Integer pageSize);

    List<ProdutoResponseDTO> searchFiltered(String term, Double min, Double max, String sort, Integer page, Integer pageSize);

}
