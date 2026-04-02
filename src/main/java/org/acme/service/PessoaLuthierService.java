package org.acme.service;

import java.util.List;

import org.acme.dto.PessoaLuthierDTO;
import org.acme.dto.PessoaLuthierResponseDTO;

public interface PessoaLuthierService {

    PessoaLuthierResponseDTO create(PessoaLuthierDTO pessoaLuthierDTO);
    void update(long id, PessoaLuthierDTO pessoaLuthierDTO);
    void delete(long id);

    List<PessoaLuthierResponseDTO> findAll();

}