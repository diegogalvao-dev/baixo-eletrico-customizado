package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

import org.acme.model.Captador;

@ApplicationScoped
public class CaptadoresRepository implements PanacheRepository<Captador> {

    public List<Captador> listByIds(List<Long> ids) {
    return list("id in ?1", ids);
}

}
