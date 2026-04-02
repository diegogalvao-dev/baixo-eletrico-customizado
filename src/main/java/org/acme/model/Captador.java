package org.acme.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Captador extends DefaultEntity{

    @Column
    private String marca;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private CaptadorPosicao captadorPosicao;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public CaptadorPosicao getCaptadorPosicao() {
        return captadorPosicao;
    }

    public void setCaptadorPosicao(CaptadorPosicao captadorPosicao) {
        this.captadorPosicao = captadorPosicao;
    }


    

}
