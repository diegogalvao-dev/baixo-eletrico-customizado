package org.acme.service;

import java.util.List;


import org.acme.dto.CaptadorAtivoDTO;
import org.acme.dto.CaptadorAtivoResponseDTO;

public interface CaptadorAtivoService {

    CaptadorAtivoResponseDTO create(CaptadorAtivoDTO captadorAtivoDTO);
    void update(long id, CaptadorAtivoDTO captadorAtivoDTO);
    void delete(long id);

    List<CaptadorAtivoResponseDTO> findAll(Integer page, Integer pageSize);
    List<CaptadorAtivoResponseDTO> search(String term, Integer page, Integer pageSize);
    long count();
    CaptadorAtivoResponseDTO findById(long id);

}