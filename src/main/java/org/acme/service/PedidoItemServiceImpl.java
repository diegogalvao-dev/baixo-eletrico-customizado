package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.acme.dto.PedidoItemDTO;
import org.acme.dto.PedidoItemResponseDTO;
import org.acme.model.Pedido;
import org.acme.model.PedidoItem;
import org.acme.model.Produto;
import org.acme.repository.PedidoItemRepository;
import org.acme.repository.PedidoRepository;
import org.acme.repository.ProdutoRepository;

import java.util.List;

@ApplicationScoped
public class PedidoItemServiceImpl implements PedidoItemService {

    @Inject
    PedidoItemRepository pedidoItemRepository;

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    ProdutoRepository produtoRepository;

    @Override
    @Transactional
    public PedidoItemResponseDTO create(PedidoItemDTO dto) {
        PedidoItem newPedidoItem = new PedidoItem();
        
        newPedidoItem.setQuantidade(dto.quantidade());
        newPedidoItem.setPrecoUnitario(dto.precoUnitario());
        newPedidoItem.setProduto(produtoRepository.findById(dto.produto()));
        newPedidoItem.setPedido(pedidoRepository.findById(dto.pedido()));

        
        pedidoItemRepository.persist(newPedidoItem);
        
        return PedidoItemResponseDTO.valueOf(newPedidoItem);
    }

    @Override
    @Transactional
    public void update(long id, PedidoItemDTO dto) {
        PedidoItem modifyPedidoItem = pedidoItemRepository.findById(id);
        if (modifyPedidoItem == null) {
            throw new NotFoundException("PedidoItem with id " + id + " not found");
        }
        
        modifyPedidoItem.setQuantidade(dto.quantidade());
        modifyPedidoItem.setPrecoUnitario(dto.precoUnitario());
        modifyPedidoItem.setProduto(produtoRepository.findById(dto.produto()));
        modifyPedidoItem.setPedido(pedidoRepository.findById(dto.pedido()));
        
    }

    @Override
    @Transactional
    public void delete(long id) {
        pedidoItemRepository.deleteById(id);
    }

    @Override
    public PedidoItemResponseDTO findById(long id) {
        PedidoItem pedidoItem = pedidoItemRepository.findById(id);
        if (pedidoItem == null) {
            throw new NotFoundException("PedidoItem with id " + id + " not found");
        }
        return PedidoItemResponseDTO.valueOf(pedidoItem);
    }

    @Override
    public List<PedidoItemResponseDTO> findAll() {
        return pedidoItemRepository.findAll().stream().map(PedidoItemResponseDTO::valueOf).toList();
    }

}
