package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.dto.BaixoCustomizadoDTO;
import org.acme.dto.BaixoCustomizadoResponseDTO;
import org.acme.model.BaixoCustomizado;
import org.acme.model.ConfiguracaoEletronica;
import org.acme.repository.BaixoCustomizadoRepository;
import org.acme.repository.ConfiguracaoEletronicaRepository;

import java.util.List;

@ApplicationScoped
public class BaixoCustomizadoServiceImpl implements BaixoCustomizadoService{

    @Inject
    BaixoCustomizadoRepository baixocustomizadoRepository;

    @Inject
    ConfiguracaoEletronicaRepository configuracaoEletronicaRepository;

    @Override
    @Transactional
    public BaixoCustomizadoResponseDTO create(BaixoCustomizadoDTO dto){

        BaixoCustomizado newBaixocustomizado = new BaixoCustomizado();

        newBaixocustomizado.setModeloBaseBaixo(dto.modeloBaseBaixo());
        newBaixocustomizado.setCorBaixo(dto.corBaixo());
        newBaixocustomizado.setPriceEstimated(dto.priceEstimated());
        newBaixocustomizado.setConfiguracaoEletronica(configuracaoEletronicaRepository.findById(dto.configuracaoEletronica()));

        baixocustomizadoRepository.persist(newBaixocustomizado);

        return BaixoCustomizadoResponseDTO.valueOf(newBaixocustomizado);

    }

    @Override
    @Transactional
    public void update(long id, BaixoCustomizadoDTO dto) {

        BaixoCustomizado modifyBaixo = baixocustomizadoRepository.findById(id);

        modifyBaixo.setModeloBaseBaixo(dto.modeloBaseBaixo());
        modifyBaixo.setCorBaixo(dto.corBaixo());
        modifyBaixo.setPriceEstimated(dto.priceEstimated());
        modifyBaixo.setConfiguracaoEletronica(configuracaoEletronicaRepository.findById(dto.configuracaoEletronica()));

    }

    @Override
    @Transactional
    public void delete(long id) {
        baixocustomizadoRepository.deleteById(id);
    }

    @Override
    public List<BaixoCustomizadoResponseDTO> findAll() {
        return baixocustomizadoRepository.findAll().stream().map(e -> BaixoCustomizadoResponseDTO.valueOf(e)).toList();
    }

}
