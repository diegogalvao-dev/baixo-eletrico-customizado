package org.acme.model;

import java.util.List;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class PessoaLuthier extends Pessoa {

    @Column(nullable = false)
    private String cnpj;

    @OneToMany(mappedBy = "pessoaLuthier")
    private List<BaixoCustomizado> baixoCustomizados;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<BaixoCustomizado> getBaixoCustomizados() {
        return baixoCustomizados;
    }

    public void setBaixoCustomizados(List<BaixoCustomizado> baixoCustomizados) {
        this.baixoCustomizados = baixoCustomizados;
    }

    

}
