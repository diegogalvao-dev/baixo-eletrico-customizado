package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.dto.BaixoCustomizadoDTO;
import org.acme.dto.BaixoCustomizadoResponseDTO;
import org.acme.model.Acessorios;
import org.acme.model.BaixoCustomizado;
import org.acme.model.Captadores;
import org.acme.model.ConfiguracaoEletronica; // Importa a entidade ConfiguracaoEletronica
import org.acme.repository.AcessoriosRepository;
import org.acme.repository.BaixoCustomizadoRepository;
import org.acme.repository.CaptadoresRepository;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BaixoCustomizadoServiceImpl implements BaixoCustomizadoService {

    @Inject
    BaixoCustomizadoRepository baixocustomizadoRepository;

    @Inject
    AcessoriosRepository acessoriosRepository;

    @Inject
    CaptadoresRepository captadoresRepository;

    @Override
    @Transactional
    public BaixoCustomizadoResponseDTO create(BaixoCustomizadoDTO dto) {

        BaixoCustomizado newBaixocustomizado = new BaixoCustomizado();

        newBaixocustomizado.setModeloBaseBaixo(dto.modeloBaseBaixo());
        newBaixocustomizado.setCorBaixo(dto.corBaixo());
        newBaixocustomizado.setPriceEstimated(dto.priceEstimated());

        // =================================================================
        // PASSO FINAL: Gerenciamento da Composição com ConfiguracaoEletronica
        // Objetivo: Criar a ConfiguracaoEletronica como parte do BaixoCustomizado.
        // =================================================================
        if (dto.configuracaoEletronica() != null) {
            // 1. Cria uma nova instância da entidade "filha".
            ConfiguracaoEletronica config = new ConfiguracaoEletronica();
            // 2. Popula os dados da "filha" com as informações do DTO aninhado.
            config.setVolumeKnobs(dto.configuracaoEletronica().volumeKnobs());
            config.setToneKnobs(dto.configuracaoEletronica().toneKnobs());
            config.setCircuitoAtivo(dto.configuracaoEletronica().circuitoAtivo());
            // 3. Associa a "filha" ao "pai".
            newBaixocustomizado.setConfiguracaoEletronica(config);
        }

        // --- Gerenciamento dos Acessórios ---
        if (dto.acessoriosListIds() != null) {
            List<Acessorios> acessoriosParaAdd = acessoriosRepository.listDeAceParaBaixo(dto.acessoriosListIds());
            for (Acessorios acc : acessoriosParaAdd) {
                newBaixocustomizado.addAcessorio(acc);
            }
        }

        // --- Gerenciamento dos Captadores ---
        if (dto.captadoresListIds() != null) {
            List<Captadores> captadoresParaAdd = captadoresRepository.listDeCapParaBaixo(dto.captadoresListIds());
            for (Captadores cap : captadoresParaAdd) {
                newBaixocustomizado.addCaptador(cap);
            }
        }

        // Ponto Chave: Persistimos APENAS o "pai" (BaixoCustomizado).
        // Graças ao `cascade = CascadeType.ALL`, o JPA automaticamente salvará a ConfiguracaoEletronica junto.
        baixocustomizadoRepository.persist(newBaixocustomizado);

        return BaixoCustomizadoResponseDTO.valueOf(newBaixocustomizado);
    }

    @Override
    @Transactional
    public void update(long id, BaixoCustomizadoDTO dto) {

        BaixoCustomizado baixo = baixocustomizadoRepository.findById(id);
        if (baixo == null) {
            return;
        }

        baixo.setModeloBaseBaixo(dto.modeloBaseBaixo());
        baixo.setCorBaixo(dto.corBaixo());
        baixo.setPriceEstimated(dto.priceEstimated());

        // =================================================================
        // PASSO FINAL: Gerenciamento da Composição no Update
        // =================================================================
        if (dto.configuracaoEletronica() != null) {
            // Busca a configuração existente. Se não existir, cria uma nova.
            ConfiguracaoEletronica config = baixo.getConfiguracaoEletronica();
            if (config == null) {
                config = new ConfiguracaoEletronica();
                baixo.setConfiguracaoEletronica(config);
            }
            // Atualiza os campos da configuração existente com os novos dados.
            // O JPA detectará essas mudanças e fará um UPDATE na tabela ConfiguracaoEletronica.
            config.setVolumeKnobs(dto.configuracaoEletronica().volumeKnobs());
            config.setToneKnobs(dto.configuracaoEletronica().toneKnobs());
            config.setCircuitoAtivo(dto.configuracaoEletronica().circuitoAtivo());
        } else {
            // Se o DTO não enviou uma configuração, removemos a existente.
            // Graças ao `orphanRemoval = true`, o JPA deletará a linha correspondente no banco.
            baixo.setConfiguracaoEletronica(null);
        }

        // --- Gerenciamento da Lista de Acessórios ---
        List<Acessorios> acessoriosAtuais = new ArrayList<>(baixo.getAcessoriosList());
        for (Acessorios accAtual : acessoriosAtuais) {
            if (!dto.acessoriosListIds().contains(accAtual.getId())) {
                baixo.removeAcessorio(accAtual);
            }
        }
        List<Acessorios> novosAcessorios = acessoriosRepository.listDeAceParaBaixo(dto.acessoriosListIds());
        for (Acessorios accNovo : novosAcessorios) {
            boolean jaExiste = baixo.getAcessoriosList().stream()
                    .anyMatch(acc -> acc.getId().equals(accNovo.getId()));
            if (!jaExiste) {
                baixo.addAcessorio(accNovo);
            }
        }

        // --- Gerenciamento da Lista de Captadores ---
        List<Captadores> captadoresAtuais = new ArrayList<>(baixo.getCaptadoresList());
        for (Captadores capAtual : captadoresAtuais) {
            if (!dto.captadoresListIds().contains(capAtual.getId())) {
                baixo.removeCaptador(capAtual);
            }
        }
        List<Captadores> novosCaptadores = captadoresRepository.listDeCapParaBaixo(dto.captadoresListIds());
        for (Captadores capNovo : novosCaptadores) {
            boolean jaExiste = baixo.getCaptadoresList().stream()
                    .anyMatch(cap -> cap.getId().equals(capNovo.getId()));
            if (!jaExiste) {
                baixo.addCaptador(capNovo);
            }
        }
    }

    @Override
    @Transactional
    public void delete(long id) {
        baixocustomizadoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<BaixoCustomizadoResponseDTO> findAll() {
        return baixocustomizadoRepository.findAll().stream().map(BaixoCustomizadoResponseDTO::valueOf).toList();
    }
}
