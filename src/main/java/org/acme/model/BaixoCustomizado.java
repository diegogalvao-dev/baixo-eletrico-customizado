package org.acme.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class BaixoCustomizado extends Produto {

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

    @OneToMany
    @JoinColumn(name = "baixocustomizado_id")
    private List<Captador> captadores;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BaixoStatus baixoStatus;

    @ManyToOne
    @JoinColumn(name = "usuarioLuthierId")
    private UsuarioLuthier usuarioLuthier;

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

    public BaixoStatus getBaixoStatus() {
        return baixoStatus;
    }

    public void setBaixoStatus(BaixoStatus baixoStatus) {
        this.baixoStatus = baixoStatus;
    }

    public UsuarioLuthier getUsuarioLuthier() {
        return usuarioLuthier;
    }

    public void setUsuarioLuthier(UsuarioLuthier usuarioLuthier) {
        this.usuarioLuthier = usuarioLuthier;
    }
}
