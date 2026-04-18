package org.acme.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class CaptadorPassivo extends Captador {

    @Column
    private Double resistencia;

    @Column
    private Integer numeroBobinas;

    public Double getResistencia() {
        return resistencia;
    }

    public void setResistencia(Double resistencia) {
        this.resistencia = resistencia;
    }

    public Integer getNumeroBobinas() {
        return numeroBobinas;
    }

    public void setNumeroBobinas(Integer numeroBobinas) {
        this.numeroBobinas = numeroBobinas;
    }
}
