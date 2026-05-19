package org.acme.repository;

import org.acme.model.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    public Usuario findByUsername(String username) {
        return find("username", username).firstResult();
    }

    public Usuario findByLoginAndSenha(String login, String senha) {
        return find("username = ?1 and senha = ?2", login, senha).firstResult();
    }
}
