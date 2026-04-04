package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.acme.dto.PedidoDTO;
import org.acme.dto.PedidoResponseDTO;
import org.acme.model.Pedido;
import org.acme.model.PessoaCliente;
import org.acme.repository.PedidoItemRepository;
import org.acme.repository.PedidoRepository;
import org.acme.repository.PessoaClienteRepository;

import java.util.List;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    PedidoItemRepository pedidoItemRepository;

    @Inject
    PessoaClienteRepository pessoaClienteRepository;

    @Override
    @Transactional
    public PedidoResponseDTO create(PedidoDTO dto) {
        Pedido newPedido = new Pedido();
        
        newPedido.setData(dto.data());
        newPedido.setValorTotal(dto.valortotal());
        newPedido.setPedidoItems(pedidoItemRepository.findByIds(dto.pedidoItemList()));

        if (dto.pessoaCliente() != null) {
            PessoaCliente pessoaCliente = pessoaClienteRepository.findById(dto.pessoaCliente());
            if (pessoaCliente == null) {
                throw new NotFoundException("PessoaCliente with id " + dto.pessoaCliente() + " not found");
            }
            newPedido.setPessoaCliente(pessoaCliente);
        }

        
        pedidoRepository.persist(newPedido);
        
        return PedidoResponseDTO.valueOf(newPedido);
    }

    @Override
    @Transactional
    public void update(long id, PedidoDTO dto) {
        Pedido modifyPedido = pedidoRepository.findById(id);
        if (modifyPedido == null) {
            throw new NotFoundException("Pedido with id " + id + " not found");
        }
        
        modifyPedido.setData(dto.data());
        modifyPedido.setValorTotal(dto.valortotal());
        modifyPedido.setPedidoItems(pedidoItemRepository.findByIds(dto.pedidoItemList()));
        
        if (dto.pessoaCliente() != null) {
            PessoaCliente pessoaCliente = pessoaClienteRepository.findById(dto.pessoaCliente());
            if (pessoaCliente == null) {
                throw new NotFoundException("PessoaCliente with id " + dto.pessoaCliente() + " not found");
            }
            modifyPedido.setPessoaCliente(pessoaCliente);
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
            throw new NotFoundException("Pedido with id " + id + " not found");
        }
        return PedidoResponseDTO.valueOf(pedido);
    }

    @Override
    @Transactional
    public List<PedidoResponseDTO> findAll() {
        return pedidoRepository.findAll().stream().map(PedidoResponseDTO::valueOf).toList();
    }

}
