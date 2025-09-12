package org.acme.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class BaixoCustomizado extends DefaultEntity {

    @Enumerated(EnumType.STRING)
    private ModeloBaseBaixo modeloBaseBaixo;

    @Enumerated(EnumType.STRING)
    private CorBaixo corBaixo;

    @Column(nullable = false)
    private Double priceEstimated;

    // --- RELACIONAMENTOS ---

    // LADO INVERSO (INVERSE SIDE) DA RELAÇÃO COM ACESSORIOS
    @OneToMany(mappedBy = "baixoCustomizadoAce", orphanRemoval = false)
    private List<Acessorios> acessoriosList = new ArrayList<>();

    /**
     * Esta anotação define que UM BaixoCustomizado pode ter MUITOS Captadores.
     * Este é o "lado inverso" da relação, pois não gerencia a chave estrangeira.
     * - mappedBy = "baixoCustomizadoCap": Esta é a parte mais importante. Ela diz ao JPA:
     *   "Para saber como esta relação funciona, olhe para a entidade Captadores, no campo chamado 'baixoCustomizadoCap'.
     *   Aquele campo é o dono e contém as informações da junção."
     *   O nome aqui deve corresponder EXATAMENTE ao nome do campo na outra classe.
     */
    @OneToMany(mappedBy = "baixoCustomizadoCap", orphanRemoval = false)
    private List<Captadores> captadoresList = new ArrayList<>();


    // --- GETTERS E SETTERS PADRÃO ---

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

    // =================================================================
    // MÉTODOS DE AJUDA PARA SINCRONIZAÇÃO (ACESSÓRIOS)
    // =================================================================

    public void addAcessorio(Acessorios acessorio) {
        this.acessoriosList.add(acessorio);
        acessorio.setBaixoCustomizadoAce(this);
    }

    public void removeAcessorio(Acessorios acessorio) {
        this.acessoriosList.remove(acessorio);
        acessorio.setBaixoCustomizadoAce(null);
    }

    // =================================================================
    // MÉTODOS DE AJUDA PARA SINCRONIZAÇÃO (CAPTADORES)
    // =================================================================

    public void addCaptador(Captadores captador) {
        this.captadoresList.add(captador);
        // A chamada aqui deve corresponder ao setter na classe Captadores
        captador.setBaixoCustomizadoCap(this);
    }

    public void removeCaptador(Captadores captador) {
        this.captadoresList.remove(captador);
        // A chamada aqui deve corresponder ao setter na classe Captadores
        captador.setBaixoCustomizadoCap(null);
    }
}
