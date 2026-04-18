package org.acme.repository;

import org.acme.model.Acessorio;
import org.acme.model.Baixo;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BaixoRepository implements PanacheRepository<Baixo> {

    public PanacheQuery<Baixo> searchByTerm(String term) {
        String q = term.toLowerCase() + "%";
        return find("lower(name) like ?1", q);
    }

}