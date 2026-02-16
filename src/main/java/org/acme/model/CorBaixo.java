package org.acme.model;


import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum CorBaixo {

    SUNBURST(1L, "Sunburst"),
    PRETO(2L, "Preto"),
    BRANCO(3L, "Branco"),
    VERMELHO(4L, "Vermelho"),
    AZUL(5L, "Azul"),
    VERDE(6L, "Verde");

    private final Long ID;
    private final String NAME;

    CorBaixo(Long id, String name){
        this.ID = id;
        this.NAME = name;
    }

    public Long getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public static CorBaixo valueOf(Long id){
        for (CorBaixo c : CorBaixo.values()){
            if (c.getID() == id){
                return c;
            }
        }
        return null;
    }

}
