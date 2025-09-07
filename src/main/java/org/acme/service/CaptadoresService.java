package org.acme.service;

import org.acme.dto.CaptadoresDTO;
import org.acme.dto.CaptadoresResponseDTO;

import java.util.List;

public interface CaptadoresService {

    CaptadoresResponseDTO create(CaptadoresDTO captadoresDTO);
    void update(long id, CaptadoresDTO captadoresDTO);
    void delete(long id);

    List<CaptadoresResponseDTO> findAll();

}
