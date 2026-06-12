package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.acme.dto.UsuarioLuthierDTO;
import org.acme.dto.UsuarioLuthierResponseDTO;
import org.acme.model.UsuarioLuthier;
import org.acme.repository.UsuarioLuthierRepository;

import java.util.List;

@ApplicationScoped
public class UsuarioLuthierServiceImpl implements UsuarioLuthierService {

    @Inject
    UsuarioLuthierRepository repository;

    @Override
    @Transactional
    public UsuarioLuthierResponseDTO create(UsuarioLuthierDTO dto) {
        UsuarioLuthier entity = new UsuarioLuthier();
        entity.setNome(dto.nome());
        entity.setEmail(dto.email());
        entity.setCnpj(dto.cnpj());
        entity.setEspecialidade(dto.especialidade());
        repository.persist(entity);
        return UsuarioLuthierResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public UsuarioLuthierResponseDTO update(long id, UsuarioLuthierDTO dto) {
        UsuarioLuthier entity = repository.findById(id);
        if (entity == null) {
            throw new NotFoundException("UsuarioLuthier não encontrado");
        }
        entity.setNome(dto.nome());
        entity.setEmail(dto.email());
        entity.setCnpj(dto.cnpj());
        entity.setEspecialidade(dto.especialidade());
        return UsuarioLuthierResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public UsuarioLuthierResponseDTO findById(long id) {
        UsuarioLuthier entity = repository.findById(id);
        if (entity == null) {
            throw new NotFoundException("UsuarioLuthier não encontrado");
        }
        return UsuarioLuthierResponseDTO.valueOf(entity);
    }

    @Override
    public UsuarioLuthierResponseDTO findByUsername(String username) {
        UsuarioLuthier entity = repository.findByUsername(username);
        if (entity == null) {
            throw new NotFoundException("UsuarioLuthier não encontrado");
        }
        return UsuarioLuthierResponseDTO.valueOf(entity);
    }

    @Override
    public List<UsuarioLuthierResponseDTO> findAll() {
        return repository.findAll().stream().map(UsuarioLuthierResponseDTO::valueOf).toList();
    }
}
