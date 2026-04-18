package org.acme.repository;

import org.acme.model.Baixo;
import org.acme.model.CaptadorAtivo;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CaptadorAtivoRepository implements PanacheRepository<CaptadorAtivo> {

    public PanacheQuery<CaptadorAtivo> searchByTerm(String term) {
        String q = term.toLowerCase() + "%";
        return find("lower(marca) like ?1", q);
    }

}