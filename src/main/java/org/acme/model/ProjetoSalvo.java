package org.acme.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProjetoSalvo extends DefaultEntity {

    @ManyToOne
    @JoinColumn(name = "usuario_cliente_id", nullable = false)
    private UsuarioCliente usuarioCliente;

    @ManyToOne
    @JoinColumn(name = "baixo_customizado_id", nullable = false)
    private BaixoCustomizado baixoCustomizado;

    public UsuarioCliente getUsuarioCliente() {
        return usuarioCliente;
    }

    public void setUsuarioCliente(UsuarioCliente usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }

    public BaixoCustomizado getBaixoCustomizado() {
        return baixoCustomizado;
    }

    public void setBaixoCustomizado(BaixoCustomizado baixoCustomizado) {
        this.baixoCustomizado = baixoCustomizado;
    }
}
