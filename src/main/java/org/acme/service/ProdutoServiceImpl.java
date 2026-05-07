package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

import org.acme.dto.ProdutoDTO;
import org.acme.dto.ProdutoResponseDTO;
import org.acme.exception.ValidationException;
import org.acme.model.Produto;
import org.acme.repository.FornecedorRepository;
import org.acme.repository.ProdutoRepository;

import java.util.List;

@ApplicationScoped
public class ProdutoServiceImpl implements ProdutoService {

    @Inject
    ProdutoRepository produtoRepository;

    @Inject
    FornecedorRepository fornecedorRepository;

    @Override
    @Transactional
    public void update(long id, ProdutoDTO dto) {
        Produto produto = produtoRepository.findById(id);
        if (produto == null) {
            throw ValidationException.of("produto", "Produto não encontrado");
        }

        produto.setName(dto.name());
        produto.setPrice(dto.price());
        produto.setQuantidadeEstoque(dto.quantidadeEstoque());
        if (dto.fornecedor() != null) {
            produto.setFornecedor(fornecedorRepository.searchByFornecedor(dto.fornecedor()).firstResult());
        }
    }

    @Override
    @Transactional
    public void delete(long id) {
        produtoRepository.deleteById(id);
    }

    @Override
    public List<ProdutoResponseDTO> findAll(Integer page, Integer pageSize) {
        int pageNumber = page == null ? 0 : page;
        int size = pageSize == null ? 100 : pageSize;
        PanacheQuery<Produto> query = produtoRepository.findAll().page(pageNumber, size);
        return query.list().stream().map(ProdutoResponseDTO::valueOf).toList();
    }

    @Override
    public List<ProdutoResponseDTO> search(String term, Integer page, Integer pageSize) {
        int pageNumber = page == null ? 0 : page;
        int size = pageSize == null ? 100 : pageSize;

        PanacheQuery<Produto> query;
        if (term == null || term.isBlank()) {
            query = produtoRepository.findAll();
        } else {
            query = produtoRepository.searchByTerm(term);
        }

        query = query.page(pageNumber, size);
        return query.list().stream().map(ProdutoResponseDTO::valueOf).toList();
    }

    @Override
    public long count() {
        return produtoRepository.findAll().count();
    }

    @Override
    public ProdutoResponseDTO findById(long id) {
        Produto produto = produtoRepository.findById(id);
        if (produto == null) {
            throw ValidationException.of("produto", "Produto não encontrado");
        }
        return ProdutoResponseDTO.valueOf(produto);
    }

}
