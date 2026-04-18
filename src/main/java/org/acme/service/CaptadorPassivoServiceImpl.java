package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.dto.CaptadorPassivoResponseDTO;
import org.acme.dto.CaptadorPassivoDTO;
import org.acme.model.CaptadorPassivo;
import org.acme.repository.CaptadorPassivoRepository;
import org.acme.exception.ValidationException;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

import java.util.List;

@ApplicationScoped
public class CaptadorPassivoServiceImpl implements CaptadorPassivoService{

    @Inject
    CaptadorPassivoRepository captadorPassivoRepository;

    @Override
    @Transactional
    public CaptadorPassivoResponseDTO create(CaptadorPassivoDTO dto){

        CaptadorPassivo newCaptadorPassivo = new CaptadorPassivo();

        newCaptadorPassivo.setMarca(dto.marca());
        newCaptadorPassivo.setPrice(dto.price());
        newCaptadorPassivo.setCaptadorPosicao(dto.captadorPosicao());
        newCaptadorPassivo.setResistencia(dto.resistencia());
        newCaptadorPassivo.setNumeroBobinas(dto.numeroBobinas());

        captadorPassivoRepository.persist(newCaptadorPassivo);

        return CaptadorPassivoResponseDTO.valueOf(newCaptadorPassivo);

    }

    @Override
    @Transactional
    public void update(long id, CaptadorPassivoDTO dto) {

        CaptadorPassivo modifyCaptadorPassivo = captadorPassivoRepository.findById(id);

        modifyCaptadorPassivo.setMarca(dto.marca());
        modifyCaptadorPassivo.setPrice(dto.price());
        modifyCaptadorPassivo.setCaptadorPosicao(dto.captadorPosicao());
        modifyCaptadorPassivo.setResistencia(dto.resistencia());
        modifyCaptadorPassivo.setNumeroBobinas(dto.numeroBobinas());
    }

    @Override
    @Transactional
    public void delete(long id) {
        captadorPassivoRepository.deleteById(id);
    }

    @Override
    public List<CaptadorPassivoResponseDTO> findAll(Integer page, Integer pageSize) {
        int pageNumber = page == null ? 0 : page;
        int size = pageSize == null ? 100 : pageSize;
        PanacheQuery<CaptadorPassivo> query = captadorPassivoRepository.findAll().page(pageNumber, size);
        return query.list().stream().map(CaptadorPassivoResponseDTO::valueOf).toList();
    }

    @Override
    public List<CaptadorPassivoResponseDTO> search(String term, Integer page, Integer pageSize) {
        int pageNumber = page == null ? 0 : page;
        int size = pageSize == null ? 100 : pageSize;

        PanacheQuery<CaptadorPassivo> query;
        if (term == null || term.isBlank()) {
            query = captadorPassivoRepository.findAll();
        } else {
            query = captadorPassivoRepository.searchByTerm(term);
        }

        query = query.page(pageNumber, size);
        return query.list().stream().map(CaptadorPassivoResponseDTO::valueOf).toList();
    }

    @Override
    public long count() {
        return captadorPassivoRepository.findAll().count();
    }

    @Override
    public CaptadorPassivoResponseDTO findById(long id) {
        CaptadorPassivo captadorPassivo = captadorPassivoRepository.findById(id);
        if (captadorPassivo == null) {
            throw ValidationException.of("captadorPassivo", "Captador passivo não encontrado");
        }
        return CaptadorPassivoResponseDTO.valueOf(captadorPassivo);
    }

}