package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.acme.dto.PedidoDTO;
import org.acme.dto.PedidoResponseDTO;
import org.acme.model.Pedido;
import org.acme.model.UsuarioCliente;
import org.acme.repository.PedidoItemRepository;
import org.acme.repository.PedidoRepository;
import org.acme.repository.UsuarioClienteRepository;
import org.acme.repository.ProdutoRepository;
import org.acme.dto.ItemCarrinhoDTO;
import org.acme.model.PedidoItem;
import org.acme.model.Produto;

import java.util.List;
import java.util.ArrayList;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    PedidoItemRepository pedidoItemRepository;

    @Inject
    UsuarioClienteRepository usuarioClienteRepository;

    @Inject
    ProdutoRepository produtoRepository;

    @Override
    @Transactional
    public PedidoResponseDTO create(PedidoDTO dto, String username) {
        Pedido newPedido = new Pedido();

        newPedido.setData(dto.data());
        newPedido.setValorTotal(dto.valortotal());
        newPedido.setEnderecoEnvio(dto.enderecoEnvio());
        newPedido.setMetodoPagamento(dto.metodoPagamento());

        // Se o front não manda, a gente pega do username do token autenticado
        UsuarioCliente usuarioCliente = null;
        
        if (dto.pessoaCliente() != null) {
            usuarioCliente = usuarioClienteRepository.findById(dto.pessoaCliente());
        } else if (username != null && !username.isBlank()) {
            usuarioCliente = usuarioClienteRepository.findByUsername(username);
        }

        if (usuarioCliente == null) {
            throw new NotFoundException("Usuario não encontrado para associar ao pedido");
        }
        
        newPedido.setUsuarioCliente(usuarioCliente);

        pedidoRepository.persist(newPedido);

        List<PedidoItem> items = new ArrayList<>();
        if (dto.pedidoItemList() != null) {
            for (ItemCarrinhoDTO itemDto : dto.pedidoItemList()) {
                PedidoItem item = new PedidoItem();
                item.setPedido(newPedido);
                item.setQuantidade(itemDto.quantidade());
                item.setPrecoUnitario(itemDto.precoUnitario());

                if (itemDto.produtoId() == null) {
                    throw new IllegalArgumentException("Item do carrinho deve ter um produtoId");
                }

                Produto produto = produtoRepository.findById(itemDto.produtoId());
                if (produto == null) {
                    throw new NotFoundException("Produto com id " + itemDto.produtoId() + " não encontrado");
                }
                item.setProduto(produto);

                pedidoItemRepository.persist(item);
                items.add(item);
            }
        }
        newPedido.setPedidoItems(items);

        return PedidoResponseDTO.valueOf(newPedido);
    }

    @Override
    @Transactional
    public void update(long id, PedidoDTO dto) {
        Pedido modifyPedido = pedidoRepository.findById(id);
        if (modifyPedido == null) {
            throw new NotFoundException("Pedido com id " + id + " não encontrado");
        }

        modifyPedido.setData(dto.data());
        modifyPedido.setValorTotal(dto.valortotal());
        modifyPedido.setEnderecoEnvio(dto.enderecoEnvio());
        modifyPedido.setMetodoPagamento(dto.metodoPagamento());

        if (dto.pessoaCliente() != null) {
            UsuarioCliente usuarioCliente = usuarioClienteRepository.findById(dto.pessoaCliente());
            if (usuarioCliente == null) {
                throw new NotFoundException("UsuarioCliente com id " + dto.pessoaCliente() + " não encontrado");
            }
            modifyPedido.setUsuarioCliente(usuarioCliente);
        }
    }

    @Override
    @Transactional
    public void delete(long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public PedidoResponseDTO findById(long id) {
        Pedido pedido = pedidoRepository.findById(id);
        if (pedido == null) {
            throw new NotFoundException("Pedido com id " + id + " não encontrado");
        }
        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    @Transactional
    public List<PedidoResponseDTO> findAll() {
        return pedidoRepository.findAll().stream().map(PedidoResponseDTO::valueOf).toList();
    }

    @Override
    @Transactional
    public List<PedidoResponseDTO> findByUser(String username) {
        UsuarioCliente usuarioCliente = usuarioClienteRepository.findByUsername(username);
        if (usuarioCliente == null) {
            throw new NotFoundException("Usuário cliente não encontrado");
        }
        return usuarioCliente.getPedidos().stream().map(PedidoResponseDTO::valueOf).toList();
    }
}
