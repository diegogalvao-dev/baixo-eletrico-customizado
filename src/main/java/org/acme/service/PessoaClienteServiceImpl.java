package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.dto.PessoaClienteResponseDTO;
import org.acme.dto.PessoaClienteDTO;
import org.acme.model.PessoaCliente;
import org.acme.model.Pedido;
import org.acme.model.BaixoCustomizado;
import org.acme.repository.PessoaClienteRepository;
import org.acme.repository.PedidoRepository;
import org.acme.repository.BaixoCustomizadoRepository;

import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class PessoaClienteServiceImpl implements PessoaClienteService{

    @Inject
    PessoaClienteRepository pessoaClienteRepository;

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    BaixoCustomizadoRepository baixoCustomizadoRepository;

    @Inject
    EntityManager entityManager;

    @Override
    @Transactional
    public PessoaClienteResponseDTO create(PessoaClienteDTO dto){

        PessoaCliente newPessoaCliente = new PessoaCliente();

        newPessoaCliente.setName(dto.name());
        newPessoaCliente.setEmail(dto.email());
        newPessoaCliente.setCpf(dto.cpf());

        List<Pedido> pedidos = dto.pedidos() == null ? List.of() : dto.pedidos().stream()
                .map(id -> entityManager.find(Pedido.class, id))
                .filter(Objects::nonNull)
                .toList();
        newPessoaCliente.setPedidos(pedidos);

        List<BaixoCustomizado> baixoCustomizados = dto.baixoCustomizados() == null ? List.of() : dto.baixoCustomizados().stream()
                .map(id -> entityManager.find(BaixoCustomizado.class, id))
                .filter(Objects::nonNull)
                .toList();
        newPessoaCliente.setBaixoCustomizados(baixoCustomizados);

        pessoaClienteRepository.persist(newPessoaCliente);

        return PessoaClienteResponseDTO.valueOf(newPessoaCliente);

    }

    @Override
    @Transactional
    public void update(long id, PessoaClienteDTO dto) {

        PessoaCliente modifyPessoaCliente = pessoaClienteRepository.findById(id);

        modifyPessoaCliente.setName(dto.name());
        modifyPessoaCliente.setEmail(dto.email());
        modifyPessoaCliente.setCpf(dto.cpf());

        List<Pedido> pedidos = dto.pedidos() == null ? List.of() : dto.pedidos().stream()
                .map(pid -> entityManager.find(Pedido.class, pid))
                .filter(Objects::nonNull)
                .toList();
        modifyPessoaCliente.setPedidos(pedidos);

        List<BaixoCustomizado> baixoCustomizados = dto.baixoCustomizados() == null ? List.of() : dto.baixoCustomizados().stream()
                .map(pid -> entityManager.find(BaixoCustomizado.class, pid))
                .filter(Objects::nonNull)
                .toList();
        modifyPessoaCliente.setBaixoCustomizados(baixoCustomizados);
    }

    @Override
    @Transactional
    public void delete(long id) {
        pessoaClienteRepository.deleteById(id);
    }

    @Override
    public List<PessoaClienteResponseDTO> findAll() {
        return pessoaClienteRepository.findAll().list().stream()
                .map(PessoaClienteResponseDTO::valueOf)
                .toList();
    }

}