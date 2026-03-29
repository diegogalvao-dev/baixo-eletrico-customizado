package org.acme.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class PessoaCliente extends Pessoa {

    @Column(nullable = false)
    private String cpf;

    @OneToMany(mappedBy = "pessoaCliente")
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "pessoaCliente")
    private List<BaixoCustomizado> baixoCustomizados;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<BaixoCustomizado> getBaixoCustomizados() {
        return baixoCustomizados;
    }

    public void setBaixoCustomizados(List<BaixoCustomizado> baixoCustomizados) {
        this.baixoCustomizados = baixoCustomizados;
    }

    

    

}
