package org.acme.repository;

import org.acme.model.UsuarioCliente;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioClienteRepository implements PanacheRepository<UsuarioCliente> {

    public UsuarioCliente findByUsername(String username) {
        return find("username", username).firstResult();
    }
}
