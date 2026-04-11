package org.acme.model;

import jakarta.persistence.*;

@Entity
public class Acessorio extends Produto{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AcessorioTipo acessorioTipo;

    @Column(nullable = false)
    private String material;

    @Column(nullable = false)
    private Double tamanho;


    public Double getTamanho() {
        return tamanho;
    }

    public void setTamanho(Double tamanho) {
        this.tamanho = tamanho;
    }

    public AcessorioTipo getAcessorioTipo() {
        return acessorioTipo;
    }

    public void setAcessorioTipo(AcessorioTipo acessorioTipo) {
        this.acessorioTipo = acessorioTipo;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
