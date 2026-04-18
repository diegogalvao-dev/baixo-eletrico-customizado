package org.acme.model;


public enum BaixoCor {

    SUNBURST(1L, "Sunburst"),
    PRETO(2L, "Preto"),
    BRANCO(3L, "Branco"),
    VERMELHO(4L, "Vermelho"),
    AZUL(5L, "Azul"),
    VERDE(6L, "Verde");

    private final Long ID;
    private final String NAME;

    BaixoCor(Long id, String name){
        this.ID = id;
        this.NAME = name;
    }

    public Long getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public static BaixoCor valueOf(Long id){
        for (BaixoCor c : BaixoCor.values()){
            if (c.getID() == id){
                return c;
            }
        }
        return null;
    }

}
