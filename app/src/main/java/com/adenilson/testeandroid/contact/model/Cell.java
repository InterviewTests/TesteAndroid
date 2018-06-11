package com.adenilson.testeandroid.contact.model;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

public class Cell {

    private String message;
    private TypeEnum type;
    private TypeFieldEnum typeField;
    private boolean isHidden;
    private int topSpacing;
    private boolean required;

    public Cell(String message, TypeEnum type, TypeFieldEnum typeField, boolean isHidden, int topSpacing, boolean required) {
        this.message = message;
        this.type = type;
        this.typeField = typeField;
        this.isHidden = isHidden;
        this.topSpacing = topSpacing;
        this.required = required;
    }

    public TypeEnum getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public int getTopSpacing() {
        return topSpacing;
    }

    public void setTopSpacing(int topSpacing) {
        this.topSpacing = topSpacing;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public TypeFieldEnum getTypeField() {
        return typeField;
    }

    public void setTypeField(TypeFieldEnum typeField) {
        this.typeField = typeField;
    }

}
