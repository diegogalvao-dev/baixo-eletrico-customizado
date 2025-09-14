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

    @OneToMany(mappedBy = "baixoCustomizadoAce", orphanRemoval = false)
    private List<Acessorios> acessoriosList = new ArrayList<>();

    @OneToMany(mappedBy = "baixoCustomizadoCap", orphanRemoval = false)
    private List<Captadores> captadoresList = new ArrayList<>();

    // =================================================================
    // LADO DONO (OWNING SIDE) DA RELAÇÃO DE COMPOSIÇÃO @OneToOne
    // =================================================================
    /**
     * Esta anotação define uma relação um-para-um com ConfiguracaoEletronica.
     * Esta é a entidade "dona", o que significa que ela é responsável por gerenciar a chave estrangeira.
     *
     * - cascade = CascadeType.ALL: Esta é a chave para a COMPOSIÇÃO. Significa que qualquer
     *   operação de persistência (salvar, atualizar, etc.) feita no BaixoCustomizado será
     *   "cascateada" para a ConfiguracaoEletronica associada. Se salvarmos o baixo, a configuração salva junto.
     *
     * - orphanRemoval = true: Se a ConfiguracaoEletronica for "desassociada" deste baixo
     *   (ex: baixo.setConfiguracaoEletronica(null)), ela se torna uma "órfã" e será AUTOMATICAMENTE
     *   removida do banco de dados. Isso garante que a "parte" não exista sem o "todo".
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    /**
     * Define a coluna de chave estrangeira que será criada na tabela "BaixoCustomizado".
     * - name: O nome da coluna no banco de dados.
     * - referencedColumnName: A coluna na tabela "ConfiguracaoEletronica" para a qual a chave estrangeira aponta (geralmente o 'id').
     */
    @JoinColumn(name = "configuracao_eletronica_id", referencedColumnName = "id")
    private ConfiguracaoEletronica configuracaoEletronica;


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

    public ConfiguracaoEletronica getConfiguracaoEletronica() {
        return configuracaoEletronica;
    }

    // Setter padrão para a relação unidirecional.
    public void setConfiguracaoEletronica(ConfiguracaoEletronica configuracaoEletronica) {
        this.configuracaoEletronica = configuracaoEletronica;
    }

    // =================================================================
    // MÉTODOS DE AJUDA PARA SINCRONIZAÇÃO
    // =================================================================

    public void addAcessorio(Acessorios acessorio) {
        this.acessoriosList.add(acessorio);
        acessorio.setBaixoCustomizadoAce(this);
    }

    public void removeAcessorio(Acessorios acessorio) {
        this.acessoriosList.remove(acessorio);
        acessorio.setBaixoCustomizadoAce(null);
    }

    public void addCaptador(Captadores captador) {
        this.captadoresList.add(captador);
        captador.setBaixoCustomizadoCap(this);
    }

    public void removeCaptador(Captadores captador) {
        this.captadoresList.remove(captador);
        captador.setBaixoCustomizadoCap(null);
    }
}
