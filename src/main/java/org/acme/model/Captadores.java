package org.acme.model;

import jakarta.persistence.*;

@Entity
public class Captadores extends DefaultEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCaptador tipoCaptador;

    @Column(nullable = false)
    private String marcaCaptador;

    @Column(nullable = false)
    private String posicao;

    // =================================================================
    // LADO DONO (OWNING SIDE) DA RELAÇÃO BIDIRECIONAL
    // =================================================================

    /**
     * Esta anotação define que MUITOS Captadores podem pertencer a UM BaixoCustomizado.
     * Este é o "lado dono" da relação porque é a entidade que contém a chave estrangeira.
     * O JPA/Hibernate só olha para este lado para saber como salvar a relação no banco de dados.
     */
    @ManyToOne
    /**
     * Especifica a coluna de junção (a chave estrangeira) no banco de dados.
     * - name = "baixo_id": Diz que na tabela "Captadores", haverá uma coluna chamada "baixo_id".
     * - nullable = true (padrão): Permite que esta coluna seja nula. Isso é essencial para nós,
     *   pois significa que um Captador pode existir no nosso catálogo sem estar associado a nenhum baixo ainda.
     */
    @JoinColumn(name = "baixo_id")
    private BaixoCustomizado baixoCustomizadoCap;

    // --- Getters e Setters --- //

    public BaixoCustomizado getBaixoCustomizadoCap() {
        return baixoCustomizadoCap;
    }

    public void setBaixoCustomizadoCap(BaixoCustomizado baixoCustomizadoCap) {
        this.baixoCustomizadoCap = baixoCustomizadoCap;
    }

    public String getMarcaCaptador() {
        return marcaCaptador;
    }

    public void setMarcaCaptador(String marcaCaptador) {
        this.marcaCaptador = marcaCaptador;
    }

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public TipoCaptador getTipoCaptador() {
        return tipoCaptador;
    }

    public void setTipoCaptador(TipoCaptador tipoCaptador) {
        this.tipoCaptador = tipoCaptador;
    }
}
