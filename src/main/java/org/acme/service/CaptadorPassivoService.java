package org.acme.service;

import java.util.List;

import org.acme.dto.CaptadorPassivoDTO;
import org.acme.dto.CaptadorPassivoResponseDTO;

public interface CaptadorPassivoService {

    CaptadorPassivoResponseDTO create(CaptadorPassivoDTO captadorPassivoDTO);
    void update(long id, CaptadorPassivoDTO captadorPassivoDTO);
    void delete(long id);

    List<CaptadorPassivoResponseDTO> findAll();

}