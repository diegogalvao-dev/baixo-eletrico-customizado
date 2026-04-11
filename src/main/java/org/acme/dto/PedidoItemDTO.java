package org.acme.dto;

public record PedidoItemDTO(

    Integer quantidade,
    Double precoUnitario,
    Long produto,
    Long pedido

) { }