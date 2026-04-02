package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.dto.CaptadorPassivoResponseDTO;
import org.acme.dto.CaptadorPassivoDTO;
import org.acme.model.CaptadorPassivo;
import org.acme.repository.CaptadorPassivoRepository;

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
    public List<CaptadorPassivoResponseDTO> findAll() {
        return captadorPassivoRepository.findAll().stream().map(e -> CaptadorPassivoResponseDTO.valueOf(e)).toList();
    }

}