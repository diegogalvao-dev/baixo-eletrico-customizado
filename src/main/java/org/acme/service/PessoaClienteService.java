package org.acme.service;

import java.util.List;

import org.acme.dto.PessoaClienteDTO;
import org.acme.dto.PessoaClienteResponseDTO;

public interface PessoaClienteService {

    PessoaClienteResponseDTO create(PessoaClienteDTO pessoaClienteDTO);
    void update(long id, PessoaClienteDTO pessoaClienteDTO);
    void delete(long id);

    List<PessoaClienteResponseDTO> findAll();

}