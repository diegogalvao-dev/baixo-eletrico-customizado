package org.acme.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UsuarioLuthier extends Usuario {

    @Column
    private String cnpj;

    @Column
    private String especialidade;

    @OneToMany(mappedBy = "usuarioLuthier")
    private List<BaixoCustomizado> baixoCustomizados = new ArrayList<>();

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public List<BaixoCustomizado> getBaixoCustomizados() {
        return baixoCustomizados;
    }

    public void setBaixoCustomizados(List<BaixoCustomizado> baixoCustomizados) {
        this.baixoCustomizados = baixoCustomizados;
    }
}
