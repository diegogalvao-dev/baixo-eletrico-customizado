package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.BaixoCustomizado;

@ApplicationScoped
public class BaixoCustomizadoRepository implements PanacheRepository<BaixoCustomizado> {


}
