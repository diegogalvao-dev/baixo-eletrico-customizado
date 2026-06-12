package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.acme.dto.UsuarioClienteDTO;
import org.acme.dto.UsuarioClienteResponseDTO;
import org.acme.model.UsuarioCliente;
import org.acme.repository.UsuarioClienteRepository;

import java.util.List;

@ApplicationScoped
public class UsuarioClienteServiceImpl implements UsuarioClienteService {

    @Inject
    UsuarioClienteRepository repository;

    @Override
    @Transactional
    public UsuarioClienteResponseDTO create(UsuarioClienteDTO dto) {
        UsuarioCliente entity = new UsuarioCliente();
        entity.setNome(dto.nome());
        entity.setEmail(dto.email());
        entity.setCpf(dto.cpf());
        repository.persist(entity);
        return UsuarioClienteResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public UsuarioClienteResponseDTO update(long id, UsuarioClienteDTO dto) {
        UsuarioCliente entity = repository.findById(id);
        if (entity == null) {
            throw new NotFoundException("UsuarioCliente não encontrado");
        }
        entity.setNome(dto.nome());
        entity.setEmail(dto.email());
        entity.setCpf(dto.cpf());
        return UsuarioClienteResponseDTO.valueOf(entity);
    }

    @Override
    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public UsuarioClienteResponseDTO findById(long id) {
        UsuarioCliente entity = repository.findById(id);
        if (entity == null) {
            throw new NotFoundException("UsuarioCliente não encontrado");
        }
        return UsuarioClienteResponseDTO.valueOf(entity);
    }

    @Override
    public UsuarioClienteResponseDTO findByUsername(String username) {
        UsuarioCliente entity = repository.findByUsername(username);
        if (entity == null) {
            throw new NotFoundException("UsuarioCliente não encontrado");
        }
        return UsuarioClienteResponseDTO.valueOf(entity);
    }

    @Override
    public List<UsuarioClienteResponseDTO> findAll() {
        return repository.findAll().stream().map(UsuarioClienteResponseDTO::valueOf).toList();
    }
}
