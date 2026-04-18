package org.acme.service;

import java.util.List;

import org.acme.dto.CaptadorPassivoDTO;
import org.acme.dto.CaptadorPassivoResponseDTO;

public interface CaptadorPassivoService {

    CaptadorPassivoResponseDTO create(CaptadorPassivoDTO captadorPassivoDTO);
    void update(long id, CaptadorPassivoDTO captadorPassivoDTO);
    void delete(long id);

    List<CaptadorPassivoResponseDTO> findAll(Integer page, Integer pageSize);
    List<CaptadorPassivoResponseDTO> search(String term, Integer page, Integer pageSize);
    long count();
    CaptadorPassivoResponseDTO findById(long id);

}