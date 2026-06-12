package org.acme.service;

import org.acme.dto.UsuarioDTO;

public interface KeycloakUserProvisioningService {
    void createUser(UsuarioDTO dto);
    void deleteUser(String username);
    void forgotPassword(String username);
    void updatePassword(String username, String newPassword);
    void updateUsername(String oldUsername, String newUsername);
}
