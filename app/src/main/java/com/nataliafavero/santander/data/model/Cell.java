package com.nataliafavero.santander.data.model;

import com.google.gson.annotations.Expose;

/**
 * Created by nataliafavero on 13/09/18.
 */

public class Cell {

    @Expose
    private int id;
    @Expose
    private int type;
    @Expose
    private String message;
    @Expose
    private String typefield;
    @Expose
    private boolean hidden;
    @Expose
    private int topSpacing;
    @Expose
    private int show;
    @Expose
    private boolean required;

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public String getTypefield() {
        return typefield;
    }

    public boolean isHidden() {
        return hidden;
    }

    public int getTopSpacing() {
        return topSpacing;
    }

    public int getShow() {
        return show;
    }

    public boolean isRequired() {
        return required;
    }

}
