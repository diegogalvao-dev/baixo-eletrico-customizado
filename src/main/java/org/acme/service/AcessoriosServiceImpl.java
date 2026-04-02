package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.dto.AcessorioResponseDTO;
import org.acme.dto.AcessoriosDTO;
import org.acme.model.Acessorio;
import org.acme.repository.AcessoriosRepository;
import org.acme.repository.FornecedorRepository;

import java.util.List;

@ApplicationScoped
public class AcessoriosServiceImpl implements AcessoriosService{

    @Inject
    AcessoriosRepository acessoriosRepository;

    @Inject
    FornecedorRepository fornecedorRepository;

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
        acessoriosRepository.deleteById(id);
    }

    @Override
    public List<AcessorioResponseDTO> findAll() {
        return acessoriosRepository.findAll().stream().map(e -> AcessorioResponseDTO.valueOf(e)).toList();
    }

}
