package org.acme.dto;

import java.time.LocalDate;
import java.util.List;

import org.acme.model.Pedido;

public record PedidoResponseDTO(
    
    Long id,
    LocalDate data,
    Double valorTotal,
    List<Long> pedidoItemList,
    Long pessoaCliente

) {

    public static PedidoResponseDTO valueOf(Pedido pedido){

        if(pedido == null){
            return null;
        }

        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getData(),
                pedido.getValorTotal(),
                pedido.getPedidoItems().stream().map(p -> p.getId()).toList(),
                pedido.getPessoaCliente().getId()
        );
    }

 } 
