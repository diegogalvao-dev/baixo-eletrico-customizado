package org.acme.service;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

import org.acme.dto.ProdutoResponseDTO;
import org.acme.model.Produto;
import org.acme.model.UsuarioCliente;
import org.acme.repository.ProdutoRepository;
import org.acme.repository.UsuarioClienteRepository;

@ApplicationScoped
public class FavoritosServiceImpl implements FavoritosService {

    @Inject
    UsuarioClienteRepository usuarioRepository;

    @Inject
    ProdutoRepository produtoRepository;

    @Override
    public List<ProdutoResponseDTO> getFavoritos(String username) {
        UsuarioCliente usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new NotFoundException("Usuário cliente não encontrado");
        }
        
        return usuario.getFavoritos().stream()
            .map(ProdutoResponseDTO::valueOf)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void adicionarFavorito(String username, Long produtoId) {
        UsuarioCliente usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new NotFoundException("Usuário cliente não encontrado");
        }
        
        Produto produto = produtoRepository.findById(produtoId);
        if (produto == null) {
            throw new NotFoundException("Produto não encontrado");
        }
        
        if (!usuario.getFavoritos().contains(produto)) {
            usuario.getFavoritos().add(produto);
            usuarioRepository.persist(usuario);
        }
    }

    @Override
    @Transactional
    public void removerFavorito(String username, Long produtoId) {
        UsuarioCliente usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new NotFoundException("Usuário cliente não encontrado");
        }
        
        Produto produto = produtoRepository.findById(produtoId);
        if (produto != null) {
            usuario.getFavoritos().remove(produto);
            usuarioRepository.persist(usuario);
        }
    }
}
