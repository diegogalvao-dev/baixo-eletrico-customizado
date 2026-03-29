package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;

import org.acme.model.Captador;

@ApplicationScoped
public class CaptadoresRepository implements PanacheRepository<Captador> {

    

}
