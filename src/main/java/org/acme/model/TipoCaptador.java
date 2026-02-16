package org.acme.model;


import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoCaptador {

    SINGLE(1L, "Single"),
    HUMBUCKER(2L, "Humbercker"),
    PIEZO(3L, "Piezo"),
    SOAPBAR(4L, "Soapbar"),
    CUSTOM(5L, "Custom");

    private final Long ID;
    private final String NAME;

    TipoCaptador(Long id, String name) {
        this.ID = id;
        this.NAME = name;
    }

    public Long getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public static TipoCaptador valueOf(Long id){
        for (TipoCaptador t : TipoCaptador.values()){
            if (t.getID() == id){
                return t;
            }
        }
        return null;
    }

}
