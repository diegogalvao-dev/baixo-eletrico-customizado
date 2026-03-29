package org.acme.dto;

import org.acme.model.Pedido;
import org.acme.model.Produto;

public record PedidoItemDTO(

    Integer quantidade,
    Double precoUnitario,
    Produto produto,
    Pedido pedido

) { }