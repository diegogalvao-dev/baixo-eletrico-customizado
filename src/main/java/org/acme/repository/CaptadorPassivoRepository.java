package org.acme.repository;

import org.acme.model.CaptadorPassivo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CaptadorPassivoRepository implements PanacheRepository<CaptadorPassivo> {

}