package org.acme.model;

import jakarta.persistence.*;

@Entity
public class ConfiguracaoEletronica extends DefaultEntity {

    @Column(nullable = false)
    private Integer volumeKnobs;

    @Column(nullable = false)
    private Integer toneKnobs;

    @Column(nullable = false)
    private Boolean circuitoAtivo;

    public Integer getVolumeKnobs() {
        return volumeKnobs;
    }

    public void setVolumeKnobs(Integer volumeKnobs) {
        this.volumeKnobs = volumeKnobs;
    }

    public Integer getToneKnobs() {
        return toneKnobs;
    }

    public void setToneKnobs(Integer toneKnobs) {
        this.toneKnobs = toneKnobs;
    }

    public Boolean getCircuitoAtivo() {
        return circuitoAtivo;
    }

    public void setCircuitoAtivo(Boolean circuitoAtivo) {
        this.circuitoAtivo = circuitoAtivo;
    }
}
