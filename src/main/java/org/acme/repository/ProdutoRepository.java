package org.acme.repository;

import org.acme.model.Produto;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoRepository implements PanacheRepository<Produto> {

    public PanacheQuery<Produto> searchByTerm(String term) {
        String q = term.toLowerCase() + "%";
        return find("lower(name) like ?1", q);
    }

}
