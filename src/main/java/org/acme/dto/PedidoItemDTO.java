package org.acme.dto;

import org.acme.model.Pedido;
import org.acme.model.Produto;

public record PedidoItemDTO(

    Integer quantidade,
    Double precoUnitario,
    Long produto,
    Long pedido

) { }