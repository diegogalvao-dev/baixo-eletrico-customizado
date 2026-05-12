package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.model.Captador;
import org.acme.repository.CaptadoresRepository;
import java.util.List;

@ApplicationScoped
public class CaptadorService {

    @Inject
    CaptadoresRepository captadoresRepository;

    @Transactional
    public Captador create(Captador captador) {
        captadoresRepository.persist(captador);
        return captador;
    }

    @Transactional
    public void update(Long id, Captador captador) {
        Captador managed = captadoresRepository.findById(id);
        if (managed != null) {
            managed.setMarca(captador.getMarca());
            managed.setPrice(captador.getPrice());
            managed.setCaptadorPosicao(captador.getCaptadorPosicao());
            // Subclass specific fields are handled by Hibernate because 'managed' is the correct subclass
            // But if we want to update specific fields of subclasses, we might need a more robust mapper.
            // For now, let's keep it simple as the model was simplified.
        }
    }

    @Transactional
    public void delete(Long id) {
        captadoresRepository.deleteById(id);
    }

    public List<Captador> findAll() {
        return captadoresRepository.listAll();
    }

    public Captador findById(Long id) {
        return captadoresRepository.findById(id);
    }
}
