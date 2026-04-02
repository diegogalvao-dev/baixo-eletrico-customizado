package org.acme.service;

import java.util.List;

import org.acme.dto.ConfiguracaoEletronicaDTO;
import org.acme.dto.ConfiguracaoEletronicaResponseDTO;

public interface ConfiguracaoEletronicaService {

    ConfiguracaoEletronicaResponseDTO create(ConfiguracaoEletronicaDTO configuracaoEletronicaDTO);
    void update(long id, ConfiguracaoEletronicaDTO configuracaoEletronicaDTO);
    void delete(long id);

    List<ConfiguracaoEletronicaResponseDTO> findAll();

}