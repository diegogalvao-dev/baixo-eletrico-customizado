package org.acme.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class BaixoCustomizado extends DefaultEntity{

    @Enumerated(EnumType.STRING)
    private ModeloBaseBaixo modeloBaseBaixo;

    @Enumerated(EnumType.STRING)
    private CorBaixo corBaixo;

    @Column(nullable = false)
    private Double priceEstimated;

    //relacionamento

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "configEletronica", referencedColumnName = "id")
    private ConfiguracaoEletronica configuracaoEletronica;

    @OneToMany(mappedBy = "baixo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Captadores> captadoresList;

    @OneToMany(mappedBy = "baixo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Acessorios> acessoriosList;


    public List<Acessorios> getAcessoriosList() {
        return acessoriosList;
    }

    public void setAcessoriosList(List<Acessorios> acessoriosList) {
        this.acessoriosList = acessoriosList;
    }

    public List<Captadores> getCaptadoresList() {
        return captadoresList;
    }

    public void setCaptadoresList(List<Captadores> captadoresList) {
        this.captadoresList = captadoresList;
    }

    public ConfiguracaoEletronica getConfiguracaoEletronica() {
        return configuracaoEletronica;
    }

    public void setConfiguracaoEletronica(ConfiguracaoEletronica configuracaoEletronica) {
        this.configuracaoEletronica = configuracaoEletronica;
    }

    public CorBaixo getCorBaixo() {
        return corBaixo;
    }

    public void setCorBaixo(CorBaixo corBaixo) {
        this.corBaixo = corBaixo;
    }

    public ModeloBaseBaixo getModeloBaseBaixo() {
        return modeloBaseBaixo;
    }

    public void setModeloBaseBaixo(ModeloBaseBaixo modeloBaseBaixo) {
        this.modeloBaseBaixo = modeloBaseBaixo;
    }

    public Double getPriceEstimated() {
        return priceEstimated;
    }

    public void setPriceEstimated(Double priceEstimated) {
        this.priceEstimated = priceEstimated;
    }
}
