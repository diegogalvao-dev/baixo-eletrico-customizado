package org.acme.repository;

import org.acme.model.PessoaCliente;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PessoaClienteRepository implements PanacheRepository<PessoaCliente> {

}