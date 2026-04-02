package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.dto.CaptadorAtivoResponseDTO;
import org.acme.dto.CaptadorAtivoDTO;
import org.acme.model.CaptadorAtivo;
import org.acme.repository.CaptadorAtivoRepository;

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
    public List<CaptadorAtivoResponseDTO> findAll() {
        return captadorAtivoRepository.findAll().stream().map(e -> CaptadorAtivoResponseDTO.valueOf(e)).toList();
    }

}