package org.acme.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.*;

@Entity
@JsonTypeInfo(
  use = JsonTypeInfo.Id.NAME, 
  include = JsonTypeInfo.As.PROPERTY, 
  property = "type") // No JSON você enviará "type": "ativo" ou "passivo"
@JsonSubTypes({
  @JsonSubTypes.Type(value = CaptadorAtivo.class, name = "ativo"),
  @JsonSubTypes.Type(value = CaptadorPassivo.class, name = "passivo")
})
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Captador extends DefaultEntity{

    @Column
    private String marca;

    @Column(nullable = false)
    private Double price;

    @Enumerated(EnumType.STRING)
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
