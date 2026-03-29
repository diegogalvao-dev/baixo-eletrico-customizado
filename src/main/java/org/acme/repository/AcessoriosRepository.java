package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Acessorio;

import java.util.List;

@ApplicationScoped
public class AcessoriosRepository implements PanacheRepository<Acessorio> {

    public List<Acessorio> listDeAceParaBaixo(List<Long> ids) {
        return list("id in ?1", ids);
    }

}
