package com.alex.testeandroid.data.entities.contact;

/**
 * Created by Alex on 27/08/18.
 */
public class Cell {

    //region FIELDS
    private int id;
    private Type type;
    private String message;
    private TypeField typefield;
    private boolean hidden;
    private float topSpacing;
    private int show;
    private boolean required;
    //endregion

    //region PROPERTIES
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

    public TypeField getTypefield() {
        return typefield;
    }

    public void setTypefield(TypeField typefield) {
        this.typefield = typefield;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public float getTopSpacing() {
        return topSpacing;
    }

    public void setTopSpacing(float topSpacing) {
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
    //endregion
}
