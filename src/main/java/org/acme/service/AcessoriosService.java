package org.acme.service;

import org.acme.dto.AcessorioResponseDTO;
import org.acme.dto.AcessoriosDTO;
import org.acme.dto.FornecedorResponseDTO;

import java.util.List;

public interface AcessoriosService {

    AcessorioResponseDTO create(AcessoriosDTO acessoriosDTO);
    AcessorioResponseDTO update(long id, AcessoriosDTO acessoriosDTO);
    void delete(long id);

    List<AcessorioResponseDTO> findAll(Integer page, Integer pageSize);
    List<AcessorioResponseDTO> search(String term, Integer page, Integer pageSize);
    List<FornecedorResponseDTO> searchFornecedor(String term);
    long count();
    AcessorioResponseDTO findById(long id);

}
