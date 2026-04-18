package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import org.acme.model.BaixoCustomizado;

@ApplicationScoped
public class BaixoCustomizadoRepository implements PanacheRepository<BaixoCustomizado> {

        public PanacheQuery<BaixoCustomizado> searchByTerm(String term) {
        String q = term.toLowerCase() + "%";
        return find("lower(name) like ?1", q);
    }

}
