package org.acme.dto;

import java.time.LocalDate;
import java.util.List;

public record PedidoDTO(

    LocalDate data,
    Double valortotal,
    List<ItemCarrinhoDTO> pedidoItemList,
    Long pessoaCliente,
    String enderecoEnvio,
    String metodoPagamento

) { }
