package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.dto.AcessorioResponseDTO;
import org.acme.dto.AcessoriosDTO;
import org.acme.model.Acessorios;
import org.acme.repository.AcessoriosRepository;

import java.util.List;

@ApplicationScoped
public class AcessoriosServiceImpl implements AcessoriosService{

    @Inject
    AcessoriosRepository acessoriosRepository;

    @Override
    @Transactional
    public AcessorioResponseDTO create(AcessoriosDTO dto){

        Acessorios newAcessorios = new Acessorios();

        newAcessorios.setTipoAcessorio(dto.tipoAcessorio());
        newAcessorios.setMarcaAcessorios(dto.marcaAcessorios());
        newAcessorios.setMaterial(dto.material());

        acessoriosRepository.persist(newAcessorios);

        return AcessorioResponseDTO.valueOf(newAcessorios);

    }

    @Override
    @Transactional
    public void update(long id, AcessoriosDTO dto) {

        Acessorios modifyAce = acessoriosRepository.findById(id);

        modifyAce.setTipoAcessorio(dto.tipoAcessorio());
        modifyAce.setMarcaAcessorios(dto.marcaAcessorios());
        modifyAce.setMaterial(dto.material());

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
