package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.dto.PessoaLuthierResponseDTO;
import org.acme.dto.PessoaLuthierDTO;
import org.acme.model.PessoaLuthier;
import org.acme.model.BaixoCustomizado;
import org.acme.repository.PessoaLuthierRepository;
import org.acme.repository.BaixoCustomizadoRepository;

import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class PessoaLuthierServiceImpl implements PessoaLuthierService{

    @Inject
    PessoaLuthierRepository pessoaLuthierRepository;

    @Inject
    BaixoCustomizadoRepository baixoCustomizadoRepository;

    @Inject
    EntityManager entityManager;

    @Override
    @Transactional
    public PessoaLuthierResponseDTO create(PessoaLuthierDTO dto){

        PessoaLuthier newPessoaLuthier = new PessoaLuthier();

        newPessoaLuthier.setName(dto.name());
        newPessoaLuthier.setEmail(dto.email());
        newPessoaLuthier.setCnpj(dto.cnpj());

        List<BaixoCustomizado> baixoCustomizados = dto.baixoCustomizados() == null ? List.of() : dto.baixoCustomizados().stream()
                .map(id -> entityManager.find(BaixoCustomizado.class, id))
                .filter(Objects::nonNull)
                .toList();
        newPessoaLuthier.setBaixoCustomizados(baixoCustomizados);

        pessoaLuthierRepository.persist(newPessoaLuthier);

        return PessoaLuthierResponseDTO.valueOf(newPessoaLuthier);

    }

    @Override
    @Transactional
    public void update(long id, PessoaLuthierDTO dto) {

        PessoaLuthier modifyPessoaLuthier = pessoaLuthierRepository.findById(id);

        modifyPessoaLuthier.setName(dto.name());
        modifyPessoaLuthier.setEmail(dto.email());
        modifyPessoaLuthier.setCnpj(dto.cnpj());

        List<BaixoCustomizado> baixoCustomizados = dto.baixoCustomizados() == null ? List.of() : dto.baixoCustomizados().stream()
                .map(pid -> entityManager.find(BaixoCustomizado.class, pid))
                .filter(Objects::nonNull)
                .toList();
        modifyPessoaLuthier.setBaixoCustomizados(baixoCustomizados);
    }

    @Override
    @Transactional
    public void delete(long id) {
        pessoaLuthierRepository.deleteById(id);
    }

    @Override
    public List<PessoaLuthierResponseDTO> findAll() {
        return pessoaLuthierRepository.findAll().list().stream()
                .map(PessoaLuthierResponseDTO::valueOf)
                .toList();
    }

}