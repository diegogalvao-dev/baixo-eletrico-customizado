package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.exception.ValidationException;
import jakarta.ws.rs.NotFoundException;
import org.acme.dto.BaixoResponseDTO;
import org.acme.dto.BaixoDTO;
import org.acme.model.Baixo;
import org.acme.model.BaixoModeloBase;
import org.acme.repository.BaixoRepository;
import org.acme.repository.FornecedorRepository;
import org.acme.repository.PedidoItemRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

import java.util.List;

@ApplicationScoped
public class BaixoServiceImpl implements BaixoService{

    @Inject
    BaixoRepository baixoRepository;

    @Inject
    FornecedorRepository fornecedorRepository;

        @Inject
    PedidoItemRepository pedidoItemRepository;

    @Override
    @Transactional
    public BaixoResponseDTO create(BaixoDTO dto){

        Baixo newBaixo = new Baixo();

        newBaixo.setBaixoModeloBase(dto.baixoModeloBase());
        newBaixo.setNumeroDeCordas(dto.numeroCordas());
        newBaixo.setBaixoCor(dto.baixoCor());
        newBaixo.setName(dto.name());
        newBaixo.setPrice(dto.price());
        newBaixo.setQuantidadeEstoque(dto.quantidadeEstoque());
        if(dto.fornecedor() != null){
            newBaixo.setFornecedor(fornecedorRepository.findById(dto.fornecedor()));
        }
        baixoRepository.persist(newBaixo);

        return BaixoResponseDTO.valueOf(newBaixo);

    }

    @Override
    @Transactional
    public void update(long id, BaixoDTO dto) {

        Baixo modifyBaixo = baixoRepository.findById(id);
        if (modifyBaixo == null) {
            throw new NotFoundException("Baixo with id " + id + " not found");
        }

        modifyBaixo.setBaixoModeloBase(dto.baixoModeloBase());
        modifyBaixo.setNumeroDeCordas(dto.numeroCordas());
        modifyBaixo.setBaixoCor(dto.baixoCor());
        modifyBaixo.setName(dto.name());
        modifyBaixo.setPrice(dto.price());
        modifyBaixo.setQuantidadeEstoque(dto.quantidadeEstoque());
        if(dto.fornecedor() != null){
            modifyBaixo.setFornecedor(fornecedorRepository.findById(dto.fornecedor()));
        }
    }

    @Override
    @Transactional
    public void delete(long id) {
        baixoRepository.deleteById(id);
    }

    @Override
    public List<BaixoResponseDTO> findAll(Integer page, Integer pageSize) {
        int pageNumber = page == null ? 0 : page;
        int size = pageSize == null ? 100 : pageSize;
        PanacheQuery<Baixo> query = baixoRepository.findAll().page(pageNumber, size);
        return query.list().stream().map(BaixoResponseDTO::valueOf).toList();
    }

    // private BaixoModeloBase getBaixoModeloBaseById(Long id) {
    //     if (id == null) return null;
    //     for (BaixoModeloBase bmb : BaixoModeloBase.values()) {
    //         if (bmb.getID().equals(id)) {
    //             return bmb;
    //         }
    //     }
    //     return null;
    // }

    @Override
    public List<BaixoResponseDTO> search(String term, Integer page, Integer pageSize) {
        int pageNumber = page == null ? 0 : page;
        int size = pageSize == null ? 100 : pageSize;

        PanacheQuery<Baixo> query;
        if (term == null || term.isBlank()) {
            query = baixoRepository.findAll();
        } else {
            query = baixoRepository.searchByTerm(term);
        }

        query = query.page(pageNumber, size);
        return query.list().stream().map(BaixoResponseDTO::valueOf).toList();
    }

    @Override
    public long count() {
        return baixoRepository.findAll().count();
    }

    @Override
    public BaixoResponseDTO findById(long id) {
        Baixo baixo = baixoRepository.findById(id);
        if (baixo == null) {
            throw ValidationException.of("baixo", "Baixo não encontrado");
        }
        return BaixoResponseDTO.valueOf(baixo);
    }

}