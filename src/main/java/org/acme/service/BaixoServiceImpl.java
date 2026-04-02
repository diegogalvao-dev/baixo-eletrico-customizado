package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.acme.dto.BaixoResponseDTO;
import org.acme.dto.BaixoDTO;
import org.acme.model.Baixo;
import org.acme.model.BaixoModeloBase;
import org.acme.repository.BaixoRepository;
import org.acme.repository.FornecedorRepository;

import java.util.List;

@ApplicationScoped
public class BaixoServiceImpl implements BaixoService{

    @Inject
    BaixoRepository baixoRepository;

    @Inject
    FornecedorRepository fornecedorRepository;

    @Override
    @Transactional
    public BaixoResponseDTO create(BaixoDTO dto){

        Baixo newBaixo = new Baixo();

        newBaixo.setBaixoModeloBase(getBaixoModeloBaseById(dto.baixoModeloBaixoId()));
        newBaixo.setNumeroDeCordas(dto.numeroCordas());
        newBaixo.setBaixoCor(dto.baixoCor());
        newBaixo.setName(dto.name());
        newBaixo.setPrice(dto.price());
        newBaixo.setQuantidadeEstoque(dto.quantidadeEstoque());
        if(dto.fornecedorId() != null){
            newBaixo.setFornecedor(fornecedorRepository.findById(dto.fornecedorId()));
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

        modifyBaixo.setBaixoModeloBase(getBaixoModeloBaseById(dto.baixoModeloBaixoId()));
        modifyBaixo.setNumeroDeCordas(dto.numeroCordas());
        modifyBaixo.setBaixoCor(dto.baixoCor());
        modifyBaixo.setName(dto.name());
        modifyBaixo.setPrice(dto.price());
        modifyBaixo.setQuantidadeEstoque(dto.quantidadeEstoque());
        if(dto.fornecedorId() != null){
            modifyBaixo.setFornecedor(fornecedorRepository.findById(dto.fornecedorId()));
        }
    }

    @Override
    @Transactional
    public void delete(long id) {
        baixoRepository.deleteById(id);
    }

    @Override
    public List<BaixoResponseDTO> findAll() {
        return baixoRepository.findAll().stream().map(e -> BaixoResponseDTO.valueOf(e)).toList();
    }

    private BaixoModeloBase getBaixoModeloBaseById(Long id) {
        if (id == null) return null;
        for (BaixoModeloBase bmb : BaixoModeloBase.values()) {
            if (bmb.getID().equals(id)) {
                return bmb;
            }
        }
        return null;
    }

}