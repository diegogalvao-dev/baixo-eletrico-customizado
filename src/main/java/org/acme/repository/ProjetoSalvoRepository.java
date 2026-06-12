package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.ProjetoSalvo;

import java.util.List;

@ApplicationScoped
public class ProjetoSalvoRepository implements PanacheRepository<ProjetoSalvo> {

    public List<ProjetoSalvo> findByUsuarioLogin(String login) {
        return find("usuarioCliente.username", login).list();
    }
}
