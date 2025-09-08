package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.dto.ConfiguracaoEletronicaDTO;
import org.acme.dto.ConfiguracaoEletronicaResponseDTO;
import org.acme.model.ConfiguracaoEletronica;
import org.acme.repository.ConfiguracaoEletronicaRepository;

import java.util.List;

@ApplicationScoped
public class ConfiguracaoEletronicaServiceImpl implements ConfiguracaoEletronicaService{

    @Inject
    ConfiguracaoEletronicaRepository configuracaoEletronicaRepository;

    @Override
    @Transactional
    public ConfiguracaoEletronicaResponseDTO create(ConfiguracaoEletronicaDTO dto){

        ConfiguracaoEletronica newConfiguracaoEletronica = new ConfiguracaoEletronica();

        newConfiguracaoEletronica.setVolumeKnobs(dto.volumeKnobs());
        newConfiguracaoEletronica.setToneKnobs(dto.toneKnobs());
        newConfiguracaoEletronica.setCircuitoAtivo(dto.circuitoAtivo());
        newConfiguracaoEletronica.setBaixo(dto.baixoCustomizado());

        configuracaoEletronicaRepository.persist(newConfiguracaoEletronica);

        return ConfiguracaoEletronicaResponseDTO.valueOf(newConfiguracaoEletronica);

    }

    @Override
    @Transactional
    public void update(long id, ConfiguracaoEletronicaDTO dto) {

        ConfiguracaoEletronica modifyConfig = configuracaoEletronicaRepository.findById(id);

        modifyConfig.setVolumeKnobs(dto.volumeKnobs());
        modifyConfig.setToneKnobs(dto.toneKnobs());
        modifyConfig.setCircuitoAtivo(dto.circuitoAtivo());
        modifyConfig.setBaixo(dto.baixoCustomizado());


    }

    @Override
    @Transactional
    public void delete(long id) {
        configuracaoEletronicaRepository.deleteById(id);
    }

    @Override
    public List<ConfiguracaoEletronicaResponseDTO> findAll() {
        return configuracaoEletronicaRepository.findAll().stream().map(e -> ConfiguracaoEletronicaResponseDTO.valueOf(e)).toList();
    }

}
