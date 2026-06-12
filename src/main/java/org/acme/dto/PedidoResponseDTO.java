package org.acme.dto;

import java.time.LocalDate;
import java.util.List;

import org.acme.model.Pedido;

public record PedidoResponseDTO(

    Long id,
    LocalDate data,
    Double valorTotal,
    String metodoPagamento,
    String enderecoEnvio,
    List<PedidoItemResponseDTO> itens,
    Long usuarioClienteId

) {

    public static PedidoResponseDTO valueOf(Pedido pedido) {

        if (pedido == null) {
            return null;
        }

        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getData(),
                pedido.getValorTotal(),
                pedido.getMetodoPagamento(),
                pedido.getEnderecoEnvio(),
                pedido.getPedidoItems() != null
                        ? pedido.getPedidoItems().stream().map(PedidoItemResponseDTO::valueOf).toList()
                        : List.of(),
                pedido.getUsuarioCliente() != null ? pedido.getUsuarioCliente().getId() : null
        );
    }
}
