package org.acme.model;

import jakarta.persistence.*;

@Entity
public class Captadores extends DefaultEntity{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCaptador tipoCaptador;

    @Column(nullable = false)
    private String marcaCaptador;

    @Column(nullable = false)
    private String posicao;

    @ManyToOne
    @JoinColumn(name = "baixo_id", nullable = false)
    private BaixoCustomizado baixoCustomizadoCap;

    public BaixoCustomizado getBaixoCustomizadoCap() {
        return baixoCustomizadoCap;
    }

    public void setBaixo(BaixoCustomizado baixoCustomizadoCap) {
        this.baixoCustomizadoCap = baixoCustomizadoCap;
    }

    public String getMarcaCaptador() {
        return marcaCaptador;
    }

    public void setMarca(String marcaCaptador) {
        this.marcaCaptador = marcaCaptador;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public TipoCaptador getTipoCaptador() {
        return tipoCaptador;
    }

    public void setTipo(TipoCaptador tipoCaptador) {
        this.tipoCaptador = tipoCaptador;
    }
}
