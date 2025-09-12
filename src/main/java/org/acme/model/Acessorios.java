package org.acme.model;

import jakarta.persistence.*;

@Entity
public class Acessorios extends DefaultEntity{

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAcessorio tipoAcessorio;

    @Column(nullable = false)
    private String marcaAcessorios;

    @Column(nullable = false)
    private String material;

    @ManyToOne
    @JoinColumn(name = "baixo_id", nullable = true)
    private BaixoCustomizado baixoCustomizadoAce;

    public BaixoCustomizado getBaixoCustomizadoAce() {
        return baixoCustomizadoAce;
    }

    public void setBaixoCustomizadoAce(BaixoCustomizado baixoCustomizadoAce) {
        this.baixoCustomizadoAce = baixoCustomizadoAce;
    }

    public String getMarcaAcessorios() {
        return marcaAcessorios;
    }

    public void setMarcaAcessorios(String marcaAcessorios) {
        this.marcaAcessorios = marcaAcessorios;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public TipoAcessorio getTipoAcessorio() {
        return tipoAcessorio;
    }

    public void setTipoAcessorio(TipoAcessorio tipoAcessorio) {
        this.tipoAcessorio = tipoAcessorio;
    }
}
