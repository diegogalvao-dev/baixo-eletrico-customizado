package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Captadores;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CaptadoresRepository implements PanacheRepository<Captadores> {

    public List<Captadores> listDeCapParaBaixo(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return new ArrayList<>(); // Retorna lista vazia se não houver IDs
        }
        // Usa o método "list" do Panache para buscar todos os captadores
        // cujos IDs estão na lista fornecida, em uma única consulta.
        return list("id in ?1", ids);
    }

}
