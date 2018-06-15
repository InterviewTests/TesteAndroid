package br.com.accenture.santander.wallacebaldenebre.model;

public class Cell {
    private int id;
    private int type;
    private String message;
    private String typeField;
    private boolean hidden;
    private int topSpacing;
    private String show;
    private boolean required;

    public Cell() {
    }

    public Cell(int id, int type, String message, String typeField, boolean hidden, int topSpacing, String show, boolean required) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.typeField = typeField;
        this.hidden = hidden;
        this.topSpacing = topSpacing;
        this.show = show;
        this.required = required;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTypeField() {
        return typeField;
    }

    public void setTypeField(String typeField) {
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

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
