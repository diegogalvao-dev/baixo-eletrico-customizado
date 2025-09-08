package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Acessorios;

@ApplicationScoped
public class AcessoriosRepository implements PanacheRepository<Acessorios> {


}
