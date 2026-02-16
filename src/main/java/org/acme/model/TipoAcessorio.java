package org.acme.model;

public enum TipoAcessorio {

    PONTE(1L, "Ponte"),
    TARRAXA(2L, "Tarraxa"),
    ESCUDO(3L, "Escudo"),
    KNOB(4L, "Knob"),
    CAPA(5L, "Capa"),
    CUSTOM(6L, "Custom");

    private final Long ID;
    private final String NAME;

    TipoAcessorio(Long id, String name) {
        this.ID = id;
        this.NAME = name;
    }

    public Long getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public static TipoAcessorio valueOf(Long id){
        for (TipoAcessorio t : TipoAcessorio.values()){
            if (t.getID() == id){
                return t;
            }
        }
        return null;
    }

}
