package org.acme.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class CaptadorAtivo extends Captador {

    @Column(nullable = false)
    private Boolean PossuiBateria;

    @Column(nullable = false)
    private Boolean PossuiAmplificador;

    public Boolean getPossuiBateria() {
        return PossuiBateria;
    }

    public void setPossuiBateria(Boolean possuiBateria) {
        PossuiBateria = possuiBateria;
    }

    public Boolean getPossuiAmplificador() {
        return PossuiAmplificador;
    }

    public void setPossuiAmplificador(Boolean possuiAmplificador) {
        PossuiAmplificador = possuiAmplificador;
    }

}
