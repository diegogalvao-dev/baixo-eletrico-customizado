package org.acme.repository;

import org.acme.model.Fornecedor;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FornecedorRepository implements PanacheRepository<Fornecedor> {

    public PanacheQuery<Fornecedor> searchByFornecedor(String term) {
        String f = term.toLowerCase() + "%";
        return find("lower(name) like ?1", f);
    }

        public PanacheQuery<Fornecedor> searchByTerm (String term) {
        String f = term.toLowerCase() + "%";
        return find("lower(name) like ?1", f);
    }

}
