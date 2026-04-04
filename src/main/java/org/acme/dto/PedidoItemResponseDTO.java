package org.acme.dto;

import org.acme.model.Pedido;
import org.acme.model.PedidoItem;
import org.acme.model.Produto;

public record PedidoItemResponseDTO(
    Long id,
    Integer quantidade,
    Double precoUnitario,
    Long produtoId,
    Long pedidoId

) { 

    public static PedidoItemResponseDTO valueOf(PedidoItem pedidoItem){

        if(pedidoItem == null){
            return null;
        }

        return new PedidoItemResponseDTO(
            pedidoItem.getId(),
            pedidoItem.getQuantidade(),
            pedidoItem.getPrecoUnitario(),
            pedidoItem.getProduto().getId(),
            pedidoItem.getPedido().getId()
        );
    }

} 
