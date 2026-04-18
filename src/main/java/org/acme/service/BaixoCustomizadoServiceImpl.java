package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.acme.dto.BaixoCustomizadoResponseDTO;
import org.acme.dto.AcessorioResponseDTO;
import org.acme.dto.BaixoCustomizadoDTO;
import org.acme.model.Acessorio;
import org.acme.model.BaixoCustomizado;
import org.acme.model.Captador;
import org.acme.model.ConfiguracaoEletronica;
import org.acme.repository.BaixoCustomizadoRepository;
import org.acme.repository.CaptadoresRepository;
import org.acme.repository.ConfiguracaoEletronicaRepository;
import org.acme.repository.PessoaClienteRepository;
import org.acme.repository.PessoaLuthierRepository;
import org.acme.exception.ValidationException;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

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
        newBaixoCustomizado.setConfiguracaoEletronica(configuracaoEletronicaRepository.findById(dto.configuracaoEletronica()));
        newBaixoCustomizado.setEstimatedPrice(dto.estimatedPrice());
        newBaixoCustomizado.setBaixoStatus(dto.baixoStatus());
        newBaixoCustomizado.setPessoaCliente(pessoaClienteRepository.findById(dto.pessoaCliente()));
        newBaixoCustomizado.setPessoaLuthier(pessoaLuthierRepository.findById(dto.pessoaLuthier()));
        newBaixoCustomizado.setCaptador(captadoresRepository.listByIds(dto.captadorList()));
        

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
        modifyBaixoCustomizado.setConfiguracaoEletronica(configuracaoEletronicaRepository.findById(dto.configuracaoEletronica()));
        modifyBaixoCustomizado.setEstimatedPrice(dto.estimatedPrice());
        modifyBaixoCustomizado.setBaixoStatus(dto.baixoStatus());
        modifyBaixoCustomizado.setPessoaCliente(pessoaClienteRepository.findById(dto.pessoaCliente()));
        modifyBaixoCustomizado.setPessoaLuthier(pessoaLuthierRepository.findById(dto.pessoaLuthier()));
        modifyBaixoCustomizado.setCaptador(captadoresRepository.listByIds(dto.captadorList()));

    }

    @Override
    @Transactional
    public void delete(long id) {
        baixoCustomizadoRepository.deleteById(id);
    }

    @Override
    public List<BaixoCustomizadoResponseDTO> findAll(Integer page, Integer pageSize) {
        int pageNumber = page == null ? 0 : page;
        int size = pageSize == null ? 100 : pageSize;
        PanacheQuery<BaixoCustomizado> query = baixoCustomizadoRepository.findAll().page(pageNumber, size);
        return query.list().stream().map(BaixoCustomizadoResponseDTO::valueOf).toList();
    }

    @Override
    public List<BaixoCustomizadoResponseDTO> search(String term, Integer page, Integer pageSize) {
        int pageNumber = page == null ? 0 : page;
        int size = pageSize == null ? 100 : pageSize;

        PanacheQuery<BaixoCustomizado> query;
        if (term == null || term.isBlank()) {
            query = baixoCustomizadoRepository.findAll();
        } else {
            query = baixoCustomizadoRepository.searchByTerm(term);
        }

        query = query.page(pageNumber, size);
        return query.list().stream().map(BaixoCustomizadoResponseDTO::valueOf).toList();
    }

    @Override
    public long count() {
        return baixoCustomizadoRepository.findAll().count();
    }

    @Override
    public BaixoCustomizadoResponseDTO findById(long id) {
        BaixoCustomizado baixoCustomizado = baixoCustomizadoRepository.findById(id);
        if (baixoCustomizado == null) {
            throw ValidationException.of("baixoCustomizado", "Baixo customizado não encontrado");
        }
        return BaixoCustomizadoResponseDTO.valueOf(baixoCustomizado);
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