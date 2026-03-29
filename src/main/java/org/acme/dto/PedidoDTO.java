package org.acme.dto;

import java.time.LocalDate;
import java.util.List;

public record PedidoDTO(

    LocalDate data,
    Double valortotal,
    List<Long> pedidoItemList,
    Long pessoaCliente

) { } 
