package org.acme.service;

import java.util.List;
import org.acme.dto.ProdutoResponseDTO;

public interface FavoritosService {
    List<ProdutoResponseDTO> getFavoritos(String username);
    void adicionarFavorito(String username, Long produtoId);
    void removerFavorito(String username, Long produtoId);
}
