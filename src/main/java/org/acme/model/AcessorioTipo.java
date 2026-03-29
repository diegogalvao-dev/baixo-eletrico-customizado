package org.acme.model;

public enum AcessorioTipo {

    CORREIA(1L, "Correia"),
    CABO(2L, "Cabo"),
    PALHETA(3L, "Palheta"),
    CASE(4L, "Case"),
    AFINADOR(5L, "Afinador"),
    SUPORTE(6L, "Suporte");

    private final Long ID;
    private final String NAME;

    AcessorioTipo(Long id, String name) {
        this.ID = id;
        this.NAME = name;
    }

    public Long getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public static AcessorioTipo valueOf(Long id){
        for (AcessorioTipo t : AcessorioTipo.values()){
            if (t.getID() == id){
                return t;
            }
        }
        return null;
    }

}
