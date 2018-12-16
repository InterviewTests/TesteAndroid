package com.avanade.santander.contato.domain.model;

/**
 * Enum responsavel por identificar o tipo de máscara deve ser usada para validar o campo
 */
public enum TypeField {
        text(1),
        telnumber(2),
        email(3);

    private int tipoCampo;

    TypeField(int tipoCampo){
        this.tipoCampo = tipoCampo;
    }

    public int getTipo() {
        return tipoCampo;
    }
}
