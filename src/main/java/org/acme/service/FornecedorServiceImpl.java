package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.dto.FornecedorResponseDTO;
import org.acme.dto.FornecedorDTO;
import org.acme.model.Fornecedor;
import org.acme.model.Produto;
import org.acme.repository.FornecedorRepository;

import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class FornecedorServiceImpl implements FornecedorService{

    @Inject
    FornecedorRepository fornecedorRepository;

    @Inject
    EntityManager entityManager;

    @Override
    @Transactional
    public FornecedorResponseDTO create(FornecedorDTO dto){

        Fornecedor newFornecedor = new Fornecedor();

        newFornecedor.setName(dto.name());
        newFornecedor.setCnpj(dto.cnpj());

        List<Produto> produtos = dto.produtos() == null ? List.of() : dto.produtos().stream()
                .map(id -> entityManager.find(Produto.class, id))
                .filter(Objects::nonNull)
                .toList();

        // Associa fornecedor aos produtos em memória antes de persistir
        produtos.forEach(p -> p.setFornecedor(newFornecedor));
        newFornecedor.setProduto(produtos);

        fornecedorRepository.persist(newFornecedor);

        return FornecedorResponseDTO.valueOf(newFornecedor);

    }

    @Override
    @Transactional
    public void update(long id, FornecedorDTO dto) {

        Fornecedor modifyAce = fornecedorRepository.findById(id);

        modifyAce.setName(dto.name());
        modifyAce.setCnpj(dto.cnpj());

        List<Produto> produtos = dto.produtos() == null ? List.of() : dto.produtos().stream()
                .map(pid -> entityManager.find(Produto.class, pid))
                .filter(Objects::nonNull)
                .toList();

        produtos.forEach(p -> p.setFornecedor(modifyAce));
        modifyAce.setProduto(produtos);

    }

    @Override
    @Transactional
    public void delete(long id) {
        fornecedorRepository.deleteById(id);
    }

    @Override
    public List<FornecedorResponseDTO> findAll() {
        // Evita usar stream direto de PanacheQuery (ResultSet ainda aberto).
        // Carrega tudo em memória e só depois mapeia para DTO.
        return fornecedorRepository.findAll().list().stream()
                .map(FornecedorResponseDTO::valueOf)
                .toList();
    }

}
