package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.dto.CaptadorAtivoResponseDTO;
import org.acme.dto.CaptadorAtivoDTO;
import org.acme.model.CaptadorAtivo;
import org.acme.repository.CaptadorAtivoRepository;
import org.acme.exception.ValidationException;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

import java.util.List;

@ApplicationScoped
public class CaptadorAtivoServiceImpl implements CaptadorAtivoService{

    @Inject
    CaptadorAtivoRepository captadorAtivoRepository;

    @Override
    @Transactional
    public CaptadorAtivoResponseDTO create(CaptadorAtivoDTO dto){

        CaptadorAtivo newCaptadorAtivo = new CaptadorAtivo();

        newCaptadorAtivo.setMarca(dto.marca());
        newCaptadorAtivo.setPrice(dto.price());
        newCaptadorAtivo.setCaptadorPosicao(dto.captadorPosicao());
        newCaptadorAtivo.setPossuiBateria(dto.possuiBateria());
        newCaptadorAtivo.setPossuiAmplificador(dto.possuiAmplificador());

        captadorAtivoRepository.persist(newCaptadorAtivo);

        return CaptadorAtivoResponseDTO.valueOf(newCaptadorAtivo);

    }

    @Override
    @Transactional
    public void update(long id, CaptadorAtivoDTO dto) {

        CaptadorAtivo modifyCaptadorAtivo = captadorAtivoRepository.findById(id);

        modifyCaptadorAtivo.setMarca(dto.marca());
        modifyCaptadorAtivo.setPrice(dto.price());
        modifyCaptadorAtivo.setCaptadorPosicao(dto.captadorPosicao());
        modifyCaptadorAtivo.setPossuiBateria(dto.possuiBateria());
        modifyCaptadorAtivo.setPossuiAmplificador(dto.possuiAmplificador());
    }

    @Override
    @Transactional
    public void delete(long id) {
        captadorAtivoRepository.deleteById(id);
    }

    @Override
    public List<CaptadorAtivoResponseDTO> findAll(Integer page, Integer pageSize) {
        int pageNumber = page == null ? 0 : page;
        int size = pageSize == null ? 100 : pageSize;
        PanacheQuery<CaptadorAtivo> query = captadorAtivoRepository.findAll().page(pageNumber, size);
        return query.list().stream().map(CaptadorAtivoResponseDTO::valueOf).toList();
    }

    @Override
    public List<CaptadorAtivoResponseDTO> search(String term, Integer page, Integer pageSize) {
        int pageNumber = page == null ? 0 : page;
        int size = pageSize == null ? 100 : pageSize;

        PanacheQuery<CaptadorAtivo> query;
        if (term == null || term.isBlank()) {
            query = captadorAtivoRepository.findAll();
        } else {
            query = captadorAtivoRepository.searchByTerm(term);
        }

        query = query.page(pageNumber, size);
        return query.list().stream().map(CaptadorAtivoResponseDTO::valueOf).toList();
    }

    @Override
    public long count() {
        return captadorAtivoRepository.findAll().count();
    }

    @Override
    public CaptadorAtivoResponseDTO findById(long id) {
        CaptadorAtivo captadorAtivo = captadorAtivoRepository.findById(id);
        if (captadorAtivo == null) {
            throw ValidationException.of("captadorAtivo", "Captador ativo não encontrado");
        }
        return CaptadorAtivoResponseDTO.valueOf(captadorAtivo);
    }

}