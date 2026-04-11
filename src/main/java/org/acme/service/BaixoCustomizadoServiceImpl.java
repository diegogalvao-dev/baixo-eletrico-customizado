package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.acme.dto.BaixoCustomizadoResponseDTO;
import org.acme.dto.BaixoCustomizadoDTO;
import org.acme.model.BaixoCustomizado;
import org.acme.model.Captador;
import org.acme.model.ConfiguracaoEletronica;
import org.acme.repository.BaixoCustomizadoRepository;
import org.acme.repository.CaptadoresRepository;
import org.acme.repository.ConfiguracaoEletronicaRepository;
import org.acme.repository.PessoaClienteRepository;
import org.acme.repository.PessoaLuthierRepository;

import java.util.List;

@ApplicationScoped
public class BaixoCustomizadoServiceImpl implements BaixoCustomizadoService{

    @Inject
    BaixoCustomizadoRepository baixoCustomizadoRepository;

    @Inject
    ConfiguracaoEletronicaRepository configuracaoEletronicaRepository;

    @Inject
    CaptadoresRepository captadoresRepository;

    @Inject
    PessoaClienteRepository pessoaClienteRepository;

    @Inject
    PessoaLuthierRepository pessoaLuthierRepository;

    @Inject
    EntityManager entityManager;

    @Override
    @Transactional
    public BaixoCustomizadoResponseDTO create(BaixoCustomizadoDTO dto){

        BaixoCustomizado newBaixoCustomizado = new BaixoCustomizado();

        newBaixoCustomizado.setBaixoModeloBase(dto.baixoModeloBase());
        newBaixoCustomizado.setDescription(dto.description());
        newBaixoCustomizado.setBaixoCor(dto.baixoCor());
        newBaixoCustomizado.setConfiguracaoEletronica(resolveConfiguracaoEletronica(dto.configuracaoEletronica()));
        newBaixoCustomizado.setEstimatedPrice(dto.estimatedPrice());
        newBaixoCustomizado.setBaixoStatus(dto.baixoStatus());

        List<Captador> novosCaptadores = captadoresRepository.listByIds(dto.captadorList());
        newBaixoCustomizado.setCaptador(novosCaptadores);

        if(dto.pessoaCliente() != null){
            newBaixoCustomizado.setPessoaCliente(pessoaClienteRepository.findById(dto.pessoaCliente()));
        }
        if(dto.pessoaLuthier() != null){
            newBaixoCustomizado.setPessoaLuthier(pessoaLuthierRepository.findById(dto.pessoaLuthier()));
        }

        baixoCustomizadoRepository.persist(newBaixoCustomizado);

        return BaixoCustomizadoResponseDTO.valueOf(newBaixoCustomizado);

    }

    @Override
    @Transactional
    public void update(long id, BaixoCustomizadoDTO dto) {

        BaixoCustomizado modifyBaixoCustomizado = baixoCustomizadoRepository.findById(id);

        modifyBaixoCustomizado.setBaixoModeloBase(dto.baixoModeloBase());
        modifyBaixoCustomizado.setDescription(dto.description());
        modifyBaixoCustomizado.setBaixoCor(dto.baixoCor());
        modifyBaixoCustomizado.setConfiguracaoEletronica(resolveConfiguracaoEletronica(dto.configuracaoEletronica()));
        modifyBaixoCustomizado.setEstimatedPrice(dto.estimatedPrice());
        modifyBaixoCustomizado.setBaixoStatus(dto.baixoStatus());

        List<Captador> novosCaptadores = captadoresRepository.listByIds(dto.captadorList());
        modifyBaixoCustomizado.setCaptador(novosCaptadores);

        // modifyBaixoCustomizado.getCaptador().clear(); // remove relações antigas
        // modifyBaixoCustomizado.getCaptador().addAll(novosCaptadores); // adiciona novas

        if(dto.pessoaCliente() != null){
            modifyBaixoCustomizado.setPessoaCliente(pessoaClienteRepository.findById(dto.pessoaCliente()));
        }
        if(dto.pessoaLuthier() != null){
            modifyBaixoCustomizado.setPessoaLuthier(pessoaLuthierRepository.findById(dto.pessoaLuthier()));
        }
    }

    @Override
    @Transactional
    public void delete(long id) {
        baixoCustomizadoRepository.deleteById(id);
    }

    @Override
    public List<BaixoCustomizadoResponseDTO> findAll() {
        return baixoCustomizadoRepository.findAll().list().stream()
                .map(BaixoCustomizadoResponseDTO::valueOf)
                .toList();
    }

    private ConfiguracaoEletronica resolveConfiguracaoEletronica(ConfiguracaoEletronica dtoConfig) {
        if (dtoConfig == null) {
            return null;
        }
        if (dtoConfig.getId() != null) {
            ConfiguracaoEletronica managed = configuracaoEletronicaRepository.findById(dtoConfig.getId());
            if (managed == null) {
                throw new NotFoundException("ConfiguracaoEletronica with id " + dtoConfig.getId() + " not found");
            }
            return managed;
        }
        // No ID means new config: the cascade settings in BaixoCustomizado will persist it.
        return dtoConfig;
    }

}