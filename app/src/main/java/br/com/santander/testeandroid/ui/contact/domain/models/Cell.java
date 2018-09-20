package br.com.santander.testeandroid.ui.contact.domain.models;

import com.google.gson.annotations.SerializedName;

public class Cell {
    private int id;
    private Type type;
    private String message;
    @SerializedName("typefield")
    private TypeField typeField;
    private boolean hidden;
    private int topSpacing;
    private int show;
    private boolean required;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TypeField getTypeField() {
        return typeField;
    }

    public void setTypeField(TypeField typeField) {
        this.typeField = typeField;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public int getTopSpacing() {
        return topSpacing;
    }

    public void setTopSpacing(int topSpacing) {
        this.topSpacing = topSpacing;
    }

    public int getShow() {
        return show;
    }

    public void setShow(int show) {
        this.show = show;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}