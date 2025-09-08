package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.ConfiguracaoEletronica;

@ApplicationScoped
public class ConfiguracaoEletronicaRepository implements PanacheRepository<ConfiguracaoEletronica> {


}
