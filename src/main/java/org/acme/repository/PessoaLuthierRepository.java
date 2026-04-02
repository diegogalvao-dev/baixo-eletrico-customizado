package org.acme.repository;

import org.acme.model.PessoaLuthier;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaLuthierRepository implements PanacheRepository<PessoaLuthier> {

}