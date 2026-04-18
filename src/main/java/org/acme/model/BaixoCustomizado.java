package org.acme.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class BaixoCustomizado extends DefaultEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BaixoModeloBase baixoModeloBase;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BaixoCor baixoCor;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private ConfiguracaoEletronica configuracaoEletronica;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "baixocustomizado_id")
    private List<Captador> captadores;

    @Column(nullable = false)
    private Double estimatedPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BaixoStatus baixoStatus;

    @ManyToOne
    @JoinColumn(name = "clientebaixoCustomizados")
    private PessoaCliente pessoaCliente;

    @ManyToOne
    @JoinColumn(name = "pessoaLuthier")
    private PessoaLuthier pessoaLuthier;

    public BaixoModeloBase getBaixoModeloBase() {
        return baixoModeloBase;
    }

    public void setBaixoModeloBase(BaixoModeloBase baixoModeloBase) {
        this.baixoModeloBase = baixoModeloBase;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BaixoCor getBaixoCor() {
        return baixoCor;
    }

    public void setBaixoCor(BaixoCor baixoCor) {
        this.baixoCor = baixoCor;
    }

    public ConfiguracaoEletronica getConfiguracaoEletronica() {
        return configuracaoEletronica;
    }

    public void setConfiguracaoEletronica(ConfiguracaoEletronica configuracaoEletronica) {
        this.configuracaoEletronica = configuracaoEletronica;
    }

    public List<Captador> getCaptador() {
        return captadores;
    }

    public void setCaptador(List<Captador> captadores) {
        this.captadores = captadores;
    }

    public Double getEstimatedPrice() {
        return estimatedPrice;
    }

    public void setEstimatedPrice(Double estimatedPrice) {
        this.estimatedPrice = estimatedPrice;
    }

    public BaixoStatus getBaixoStatus() {
        return baixoStatus;
    }

    public void setBaixoStatus(BaixoStatus baixoStatus) {
        this.baixoStatus = baixoStatus;
    }

    public PessoaCliente getPessoaCliente() {
        return pessoaCliente;
    }

    public void setPessoaCliente(PessoaCliente pessoaCliente) {
        this.pessoaCliente = pessoaCliente;
    }

    public PessoaLuthier getPessoaLuthier() {
        return pessoaLuthier;
    }

    public void setPessoaLuthier(PessoaLuthier pessoaLuthier) {
        this.pessoaLuthier = pessoaLuthier;
    }

    
    
    
}
