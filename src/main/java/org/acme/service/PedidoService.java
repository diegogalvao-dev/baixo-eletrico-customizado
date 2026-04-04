package org.acme.service;

import java.util.List;

import org.acme.dto.PedidoDTO;
import org.acme.dto.PedidoResponseDTO;

public interface PedidoService {

    PedidoResponseDTO create(PedidoDTO pedidoDTO);
    void update(long id, PedidoDTO pedidoDTO);
    void delete(long id);
    PedidoResponseDTO findById(long id);
    List<PedidoResponseDTO> findAll();

}
