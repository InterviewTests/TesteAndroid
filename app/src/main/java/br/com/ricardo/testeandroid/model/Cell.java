package br.com.ricardo.testeandroid.model;

public class Cell {

    private int id;
    private int type;
    private String message;
    private String typeField;
    private boolean hidden;
    private int topSpacing;
    private int show;

    public Cell() {
    }

    public Cell(int id, int type, String message, String typeField, boolean hidden, int topSpacing, int show) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.typeField = typeField;
        this.hidden = hidden;
        this.topSpacing = topSpacing;
        this.show = show;
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

    public int getShow() {
        return show;
    }

    public void setShow(int show) {
        this.show = show;
    }
}
