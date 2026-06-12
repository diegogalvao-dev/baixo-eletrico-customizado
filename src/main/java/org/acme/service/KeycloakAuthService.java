package org.acme.service;

import org.acme.dto.AuthResponseDTO;
import org.acme.dto.AuthUsuarioDTO;

public interface KeycloakAuthService {
    AuthResponseDTO login(AuthUsuarioDTO authDTO);
}
