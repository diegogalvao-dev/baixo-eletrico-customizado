package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Acessorio;

import java.util.List;

@ApplicationScoped
public class AcessoriosRepository implements PanacheRepository<Acessorio> {

    public PanacheQuery<Acessorio> searchByTerm(String term) {
        String q = term.toLowerCase() + "%";
        return find("lower(name) like ?1 or lower(material) like ?1", q);
    }

    public List<Acessorio> listDeAceParaBaixo(List<Long> ids) {
        return list("id in ?1", ids);
    }

}
