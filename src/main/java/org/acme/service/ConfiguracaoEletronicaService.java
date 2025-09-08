package org.acme.service;

import org.acme.dto.ConfiguracaoEletronicaDTO;
import org.acme.dto.ConfiguracaoEletronicaResponseDTO;
import org.acme.model.ConfiguracaoEletronica;

import java.util.List;

public interface ConfiguracaoEletronicaService {

    ConfiguracaoEletronicaResponseDTO create(ConfiguracaoEletronicaDTO configuracaoEletronicaDTO);
    void update(long id, ConfiguracaoEletronicaDTO configuracaoEletronicaDTO);
    void delete(long id);

    List<ConfiguracaoEletronicaResponseDTO> findAll();

}
