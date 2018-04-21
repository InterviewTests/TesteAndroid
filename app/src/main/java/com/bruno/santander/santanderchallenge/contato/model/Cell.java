package com.bruno.santander.santanderchallenge.contato.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Cell {

    @JsonProperty("id")
    private int id;
    @JsonProperty("type")
    private int type;
    @JsonProperty("message")
    private String message;
    @JsonProperty("typefield")
    private String typeField; //It can be int (check on demand)
    @JsonProperty("hidden")
    private boolean hidden;
    @JsonProperty("topSpacing")
    private double topSpacing;
    @JsonProperty("show")
    private int show;
    @JsonProperty("required")
    private boolean required;

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

    public double getTopSpacing() {
        return topSpacing;
    }

    public void setTopSpacing(double topSpacing) {
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
