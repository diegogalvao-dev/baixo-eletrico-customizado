package org.acme.model;

public enum CaptadorPosicao {

    BRANCO(1L, "Branco"),
    MEIO(2L, "Meio"),
    PONTE(3L, "Ponte");

    private final Long ID;
    private final String NAME;

    CaptadorPosicao(Long id, String name) {
        this.ID = id;
        this.NAME = name;
    }

    public Long getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public static CaptadorPosicao valueOf(Long id){
        for (CaptadorPosicao t : CaptadorPosicao.values()){
            if (t.getID() == id){
                return t;
            }
        }
        return null;
    }

}
