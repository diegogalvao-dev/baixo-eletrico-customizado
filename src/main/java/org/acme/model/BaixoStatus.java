package org.acme.model;

public enum StatusConstrucao {

    PROJETANDO(1L, "PROJETANDO"),
    EM_CONSTRUCAO(2L, "EM CONSTRUÇÃO"),
    EM_TESTE(3L, "EM TESTE"),
    FINALIZADO(4L, "FINALIZADO"),
    ENTREGUE(5L, "ENTREGUE");

    private final Long ID;
    private final String NAME;

    StatusConstrucao(Long id, String name) {
        this.ID = id;
        this.NAME = name;
    }

    public Long getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public static StatusConstrucao valueOf(Long id){
        for (StatusConstrucao t : StatusConstrucao.values()){
            if (t.getID() == id){
                return t;
            }
        }
        return null;
    }
    
}
