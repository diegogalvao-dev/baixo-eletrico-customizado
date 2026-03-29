package org.acme.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Baixo extends Produto {

    @Column(nullable = false)
    private BaixoModeloBase baixoModeloBase;

    @Column(nullable = false)
    private Integer numeroDeCordas;

    @Column(nullable = false)
    private BaixoCor baixoCor;


    public BaixoModeloBase getBaixoModeloBase() {
        return baixoModeloBase;
    }

    public void setBaixoModeloBase(BaixoModeloBase baixoModeloBase) {
        this.baixoModeloBase = baixoModeloBase;
    }

    public Integer getNumeroDeCordas() {
        return numeroDeCordas;
    }

    public void setNumeroDeCordas(Integer numeroDeCordas) {
        this.numeroDeCordas = numeroDeCordas;
    }

    public BaixoCor getBaixoCor() {
        return baixoCor;
    }

    public void setBaixoCor(BaixoCor baixoCor) {
        this.baixoCor = baixoCor;
    }
    
}
