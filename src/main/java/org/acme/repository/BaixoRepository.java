package org.acme.repository;

import org.acme.model.Baixo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BaixoRepository implements PanacheRepository<Baixo> {

}