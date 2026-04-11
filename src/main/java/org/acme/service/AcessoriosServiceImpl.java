package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.dto.AcessorioResponseDTO;
import org.acme.dto.AcessoriosDTO;
import org.acme.exception.ValidationException;
import org.acme.model.Acessorio;
import org.acme.repository.AcessoriosRepository;
import org.acme.repository.FornecedorRepository;
import org.acme.repository.PedidoItemRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

import java.util.List;

@ApplicationScoped
public class AcessoriosServiceImpl implements AcessoriosService{

    @Inject
    AcessoriosRepository acessoriosRepository;

    @Inject
    FornecedorRepository fornecedorRepository;

    @Inject
    PedidoItemRepository pedidoItemRepository;

    @Override
    @Transactional
    public AcessorioResponseDTO create(AcessoriosDTO dto){

        Acessorio newAcessorio = new Acessorio();

        newAcessorio.setAcessorioTipo(dto.acessorioTipo());
        newAcessorio.setMaterial(dto.material());
        newAcessorio.setTamanho(dto.tamanho());
        newAcessorio.setName(dto.name());
        newAcessorio.setPrice(dto.price());
        newAcessorio.setQuantidadeEstoque(dto.quantidadeEstoque());
        if(dto.fornecedor() != null){
            newAcessorio.setFornecedor(fornecedorRepository.findById(dto.fornecedor()));
        }    
        acessoriosRepository.persist(newAcessorio);

        return AcessorioResponseDTO.valueOf(newAcessorio);

    }

    @Override
    @Transactional
    public void update(long id, AcessoriosDTO dto) {

        Acessorio modifyAce = acessoriosRepository.findById(id);

        modifyAce.setAcessorioTipo(dto.acessorioTipo());
        modifyAce.setMaterial(dto.material());
        modifyAce.setTamanho(dto.tamanho());
        modifyAce.setName(dto.name());
        modifyAce.setPrice(dto.price());
        modifyAce.setQuantidadeEstoque(dto.quantidadeEstoque());
        if(dto.fornecedor() != null){
            modifyAce.setFornecedor(fornecedorRepository.findById(dto.fornecedor()));
        }
    }

    @Override
    @Transactional
    public void delete(long id) {
        if (pedidoItemRepository.countByProdutoId(id) > 0) {
            throw ValidationException.of("acessorio", "Não é possível deletar este acessório pois ele está vinculado a pedidos");
        }
        
        Acessorio acessorio = acessoriosRepository.findById(id);
        if (acessorio != null) {
            acessoriosRepository.delete(acessorio);
        }
    }

    @Override
    public List<AcessorioResponseDTO> findAll(Integer page, Integer pageSize) {
        int pageNumber = page == null ? 0 : page;
        int size = pageSize == null ? 100 : pageSize;
        PanacheQuery<Acessorio> query = acessoriosRepository.findAll().page(pageNumber, size);
        return query.list().stream().map(AcessorioResponseDTO::valueOf).toList();
    }

    @Override
    public List<AcessorioResponseDTO> search(String term, Integer page, Integer pageSize) {
        int pageNumber = page == null ? 0 : page;
        int size = pageSize == null ? 100 : pageSize;

        PanacheQuery<Acessorio> query;
        if (term == null || term.isBlank()) {
            query = acessoriosRepository.findAll();
        } else {
            query = acessoriosRepository.searchByTerm(term);
        }

        query = query.page(pageNumber, size);
        return query.list().stream().map(AcessorioResponseDTO::valueOf).toList();
    }

    @Override
    public long count() {
        return acessoriosRepository.findAll().count();
    }

    @Override
    public AcessorioResponseDTO findById(long id) {
        Acessorio acessorio = acessoriosRepository.findById(id);
        if (acessorio == null) {
            throw ValidationException.of("acessorio", "Acessório não encontrado");
        }
        return AcessorioResponseDTO.valueOf(acessorio);
    }

}
