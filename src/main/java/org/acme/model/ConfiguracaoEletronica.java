package org.acme.model;

import jakarta.persistence.*;

@Entity
public class ConfiguracaoEletronica extends DefaultEntity{

    @Column(name = "volume_knobs", nullable = false)
    private Integer volumeKnobs;

    @Column(name = "tone_knobs", nullable = false)
    private Integer toneKnobs;

    @Column(name = "circuito_ativo", nullable = false)
    private Boolean circuitoAtivo;

    @OneToOne(mappedBy = "configuracaoEletronica")
    private BaixoCustomizado baixoCustomizadoConfig;


    public BaixoCustomizado getBaixoCustomizadoConfig() {
        return baixoCustomizadoConfig;
    }

    public void setBaixo(BaixoCustomizado baixoCustomizadoConfig) {
        this.baixoCustomizadoConfig = baixoCustomizadoConfig;
    }

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
