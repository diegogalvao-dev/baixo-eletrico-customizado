package org.acme.model;


import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ModeloBaseBaixo {

    JAZZ_BASS(1L, "Jazz Bass"),
    PRECISION(2L, "Precision"),
    STINGRAY(3L, "Stingray"),
    THUNDERBIRD(4L, "Thunderbird"),
    CUSTOM(5L, "Custom");

    private final Long ID;
    private final String NAME;

    ModeloBaseBaixo(Long id, String name){
        this.ID = id;
        this.NAME = name;
    }

    public Long getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public static ModeloBaseBaixo valueOf(Long id){
        for (ModeloBaseBaixo m : ModeloBaseBaixo.values()){
            if (m.getID() == id){
                return m;
            }
        }
        return null;
    }

}
