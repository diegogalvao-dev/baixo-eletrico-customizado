package org.acme.model;

import jakarta.persistence.*;

/**
 * Representa a configuração eletrônica de um baixo.
 * No nosso design (Composição), esta entidade é uma "parte" inseparável de um BaixoCustomizado.
 * Ela não tem um ciclo de vida independente e é gerenciada inteiramente através do BaixoCustomizado.
 * Por isso, ela não precisa de um link de volta para o seu "pai".
 */
@Entity
public class ConfiguracaoEletronica extends DefaultEntity {

    @Column(name = "volume_knobs", nullable = false)
    private Integer volumeKnobs;

    @Column(name = "tone_knobs", nullable = false)
    private Integer toneKnobs;

    @Column(name = "circuito_ativo", nullable = false)
    private Boolean circuitoAtivo;

    // --- Getters e Setters --- //

    public Boolean getCircuitoAtivo() {
        return circuitoAtivo;
    }

    public void setCircuitoAtivo(Boolean circuitoAtivo) {
        this.circuitoAtivo = circuitoAtivo;
    }

    public Integer getToneKnobs() {
        return toneKnobs;
    }

    public void setToneKnobs(Integer toneKnobs) {
        this.toneKnobs = toneKnobs;
    }

    public Integer getVolumeKnobs() {
        return volumeKnobs;
    }

    public void setVolumeKnobs(Integer volumeKnobs) {
        this.volumeKnobs = volumeKnobs;
    }
}
