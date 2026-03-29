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



}
