package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.acme.dto.BaixoCustomizadoResponseDTO;
import org.acme.dto.BaixoCustomizadoDTO;
import org.acme.model.BaixoCustomizado;
import org.acme.model.Captador;
import org.acme.repository.BaixoCustomizadoRepository;
import org.acme.repository.CaptadoresRepository;
import org.acme.repository.ConfiguracaoEletronicaRepository;
import org.acme.repository.UsuarioLuthierRepository;
import org.acme.repository.ProjetoSalvoRepository;
import org.acme.repository.UsuarioClienteRepository;
import org.acme.model.ProjetoSalvo;
import org.acme.model.UsuarioCliente;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.acme.exception.ValidationException;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

import java.util.List;

@ApplicationScoped
public class BaixoCustomizadoServiceImpl implements BaixoCustomizadoService {

    @Inject
    BaixoCustomizadoRepository baixoCustomizadoRepository;

    @Inject
    ConfiguracaoEletronicaRepository configuracaoEletronicaRepository;

    @Inject
    CaptadoresRepository captadoresRepository;

    @Inject
    UsuarioLuthierRepository usuarioLuthierRepository;

    @Inject
    ProjetoSalvoRepository projetoSalvoRepository;

    @Inject
    UsuarioClienteRepository usuarioClienteRepository;

    @Inject
    JsonWebToken jwt;

    @Inject
    EntityManager entityManager;

    @Override
    @Transactional
    public BaixoCustomizadoResponseDTO create(BaixoCustomizadoDTO dto) {

        BaixoCustomizado newBaixoCustomizado = new BaixoCustomizado();

        newBaixoCustomizado.setName(dto.name());
        newBaixoCustomizado.setPrice(dto.price());
        newBaixoCustomizado.setBaixoModeloBase(dto.baixoModeloBase());
        newBaixoCustomizado.setDescription(dto.description());
        newBaixoCustomizado.setBaixoCor(dto.baixoCor());
        newBaixoCustomizado.setConfiguracaoEletronica(configuracaoEletronicaRepository.findById(dto.configuracaoEletronica()));
        newBaixoCustomizado.setBaixoStatus(dto.baixoStatus());

        if (dto.usuarioLuthier() != null) {
            newBaixoCustomizado.setUsuarioLuthier(usuarioLuthierRepository.findById(dto.usuarioLuthier()));
        }

        newBaixoCustomizado.setCaptador(captadoresRepository.listByIds(dto.captadorList()));

        baixoCustomizadoRepository.persist(newBaixoCustomizado);

        String login = resolveUsername();
        if (login != null && !login.isBlank()) {
            UsuarioCliente cliente = usuarioClienteRepository.findByUsername(login);
            if (cliente != null) {
                ProjetoSalvo projetoSalvo = new ProjetoSalvo();
                projetoSalvo.setUsuarioCliente(cliente);
                projetoSalvo.setBaixoCustomizado(newBaixoCustomizado);
                projetoSalvoRepository.persist(projetoSalvo);
            }
        }

        return BaixoCustomizadoResponseDTO.valueOf(newBaixoCustomizado);
    }

    @Override
    @Transactional
    public BaixoCustomizadoResponseDTO update(long id, BaixoCustomizadoDTO dto) {

        BaixoCustomizado modifyBaixoCustomizado = baixoCustomizadoRepository.findById(id);

        modifyBaixoCustomizado.setName(dto.name());
        modifyBaixoCustomizado.setPrice(dto.price());
        modifyBaixoCustomizado.setBaixoModeloBase(dto.baixoModeloBase());
        modifyBaixoCustomizado.setDescription(dto.description());
        modifyBaixoCustomizado.setBaixoCor(dto.baixoCor());
        modifyBaixoCustomizado.setConfiguracaoEletronica(configuracaoEletronicaRepository.findById(dto.configuracaoEletronica()));
        modifyBaixoCustomizado.setBaixoStatus(dto.baixoStatus());

        if (dto.usuarioLuthier() != null) {
            modifyBaixoCustomizado.setUsuarioLuthier(usuarioLuthierRepository.findById(dto.usuarioLuthier()));
        }

        List<Captador> novosCaptadores = captadoresRepository.listByIds(dto.captadorList());
        modifyBaixoCustomizado.getCaptador().clear();
        modifyBaixoCustomizado.getCaptador().addAll(novosCaptadores);

        return BaixoCustomizadoResponseDTO.valueOf(modifyBaixoCustomizado);
    }

    @Override
    @Transactional
    public void delete(long id) {
        baixoCustomizadoRepository.deleteById(id);
    }

    @Override
    public List<BaixoCustomizadoResponseDTO> findAll(Integer page, Integer pageSize) {
        int pageNumber = page == null ? 0 : page;
        int size = pageSize == null ? 100 : pageSize;
        PanacheQuery<BaixoCustomizado> query = baixoCustomizadoRepository.findAll().page(pageNumber, size);
        return query.list().stream().map(BaixoCustomizadoResponseDTO::valueOf).toList();
    }

    @Override
    public List<BaixoCustomizadoResponseDTO> search(String term, Integer page, Integer pageSize) {
        int pageNumber = page == null ? 0 : page;
        int size = pageSize == null ? 100 : pageSize;

        PanacheQuery<BaixoCustomizado> query;
        if (term == null || term.isBlank()) {
            query = baixoCustomizadoRepository.findAll();
        } else {
            query = baixoCustomizadoRepository.searchByTerm(term);
        }

        query = query.page(pageNumber, size);
        return query.list().stream().map(BaixoCustomizadoResponseDTO::valueOf).toList();
    }

    @Override
    public long count() {
        return baixoCustomizadoRepository.findAll().count();
    }

    @Override
    public BaixoCustomizadoResponseDTO findById(long id) {
        BaixoCustomizado baixoCustomizado = baixoCustomizadoRepository.findById(id);
        if (baixoCustomizado == null) {
            throw ValidationException.of("baixoCustomizado", "Baixo customizado não encontrado");
        }
        return BaixoCustomizadoResponseDTO.valueOf(baixoCustomizado);
    }

    @Override
    public List<BaixoCustomizadoResponseDTO> getMyProjetos(String username) {
        List<ProjetoSalvo> salvos = projetoSalvoRepository.findByUsuarioLogin(username);
        return salvos.stream()
                .map(ProjetoSalvo::getBaixoCustomizado)
                .map(BaixoCustomizadoResponseDTO::valueOf)
                .toList();
    }

    private String resolveUsername() {
        if (jwt == null) return null;
        try {
            String preferredUsername = jwt.getClaim("preferred_username");
            if (preferredUsername != null && !preferredUsername.isBlank()) {
                return preferredUsername;
            }
            String upn = jwt.getClaim("upn");
            if (upn != null && !upn.isBlank()) {
                return upn;
            }
            return jwt.getSubject();
        } catch (Exception e) {
            return null; // Caso não haja contexto ativo ou token
        }
    }
}