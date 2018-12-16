package com.avanade.santander.contato.domain.model;

/**
 * Enum responsável por identificar o tipo de campos deverá ser usado
 */
public enum Type {
    field(1),
    text(2),
    image(3),
    checkbox(4),
    send(5);

    private final int tipo;

    Type(int tipo){
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }
}
