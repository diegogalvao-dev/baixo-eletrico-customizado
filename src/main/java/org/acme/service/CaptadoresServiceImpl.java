package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.dto.CaptadoresDTO;
import org.acme.dto.CaptadoresResponseDTO;
import org.acme.model.Captadores;
import org.acme.repository.CaptadoresRepository;

import java.util.List;

@ApplicationScoped
public class CaptadoresServiceImpl implements CaptadoresService{

    @Inject
    CaptadoresRepository captadoresRepository;

    @Override
    @Transactional
    public CaptadoresResponseDTO create(CaptadoresDTO dto){

        Captadores newCaptadores = new Captadores();

        newCaptadores.setTipoCaptador(dto.tipoCaptador());
        newCaptadores.setMarcaCaptador(dto.marcaCaptador());
        newCaptadores.setPosicao(dto.posicao());

        captadoresRepository.persist(newCaptadores);

        return CaptadoresResponseDTO.valueOf(newCaptadores);

    }

    @Override
    @Transactional
    public void update(long id, CaptadoresDTO dto) {

        Captadores modifyCap = captadoresRepository.findById(id);

        modifyCap.setTipoCaptador(dto.tipoCaptador());
        modifyCap.setMarcaCaptador(dto.marcaCaptador());
        modifyCap.setPosicao(dto.posicao());

    }

    @Override
    @Transactional
    public void delete(long id) {
        captadoresRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<CaptadoresResponseDTO> findAll() {
        return captadoresRepository.findAll().stream().map(e -> CaptadoresResponseDTO.valueOf(e)).toList();
    }

}
