package org.acme.service;

import java.util.List;

import org.acme.dto.PedidoItemDTO;
import org.acme.dto.PedidoItemResponseDTO;

public interface PedidoItemService {

    PedidoItemResponseDTO create(PedidoItemDTO pedidoItemDTO);
    void update(long id, PedidoItemDTO pedidoItemDTO);
    void delete(long id);
    PedidoItemResponseDTO findById(long id);
    List<PedidoItemResponseDTO> findAll();

}
