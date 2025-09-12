package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.dto.BaixoCustomizadoDTO;
import org.acme.dto.BaixoCustomizadoResponseDTO;
import org.acme.model.Acessorios;
import org.acme.model.BaixoCustomizado;
import org.acme.model.Captadores; // Importa a entidade Captadores
import org.acme.repository.AcessoriosRepository;
import org.acme.repository.BaixoCustomizadoRepository;
import org.acme.repository.CaptadoresRepository; // Importa o repositório de Captadores

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BaixoCustomizadoServiceImpl implements BaixoCustomizadoService {

    @Inject
    BaixoCustomizadoRepository baixocustomizadoRepository;

    @Inject
    AcessoriosRepository acessoriosRepository;

    // PASSO 1: Injetar o repositório de Captadores
    @Inject
    CaptadoresRepository captadoresRepository;

    @Override
    @Transactional
    public BaixoCustomizadoResponseDTO create(BaixoCustomizadoDTO dto) {

        BaixoCustomizado newBaixocustomizado = new BaixoCustomizado();

        newBaixocustomizado.setModeloBaseBaixo(dto.modeloBaseBaixo());
        newBaixocustomizado.setCorBaixo(dto.corBaixo());
        newBaixocustomizado.setPriceEstimated(dto.priceEstimated());

        // --- Gerenciamento dos Acessórios ---
        if (dto.acessoriosListIds() != null) {
            List<Acessorios> acessoriosParaAdd = acessoriosRepository.listDeAceParaBaixo(dto.acessoriosListIds());
            for (Acessorios acc : acessoriosParaAdd) {
                newBaixocustomizado.addAcessorio(acc);
            }
        }

        // PASSO 2: Adicionar a lógica de criação para os Captadores
        // --- Gerenciamento dos Captadores ---
        if (dto.captadoresListIds() != null) {
            // Assumindo que você criará um método `listDeCapParaBaixo` em CaptadoresRepository,
            // assim como fez para AcessoriosRepository.
            List<Captadores> captadoresParaAdd = captadoresRepository.listDeCapParaBaixo(dto.captadoresListIds());
            for (Captadores cap : captadoresParaAdd) {
                newBaixocustomizado.addCaptador(cap);
            }
        }

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

        // PASSO 3: Adicionar a lógica de update para os Captadores
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
