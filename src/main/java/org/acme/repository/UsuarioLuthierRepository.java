package org.acme.repository;

import org.acme.model.UsuarioLuthier;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioLuthierRepository implements PanacheRepository<UsuarioLuthier> {

    public UsuarioLuthier findByUsername(String username) {
        return find("username", username).firstResult();
    }
}
