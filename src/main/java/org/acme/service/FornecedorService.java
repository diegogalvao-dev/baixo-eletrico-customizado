package org.acme.service;

import java.util.List;

import org.acme.dto.BaixoResponseDTO;
import org.acme.dto.FornecedorDTO;
import org.acme.dto.FornecedorResponseDTO;

public interface FornecedorService {

    FornecedorResponseDTO create(FornecedorDTO fornecedorDTO);
    void update(long id, FornecedorDTO fornecedorDTO);
    void delete(long id);

    List<FornecedorResponseDTO> findAll(Integer page, Integer pageSize);
    List<FornecedorResponseDTO> search(String term, Integer page, Integer pageSize);
    long count();
    FornecedorResponseDTO findById(long id);


}
