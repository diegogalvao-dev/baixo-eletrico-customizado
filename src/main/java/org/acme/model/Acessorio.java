package org.acme.model;

import jakarta.persistence.*;

@Entity
public class Acessorios extends DefaultEntity{

    @Column(nullable = false)
    private TipoAcessorio tipoAcessorio;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String materia

    public TipoAcessorio getTipoAcessorio() {
        return tipoAcessorio;
    }

    public void setTipoAcessorio(TipoAcessorio tipoAcessorio) {
        this.tipoAcessorio = tipoAcessorio;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
