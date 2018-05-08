package rrzaniolo.testandroidsantander.network.models.ui;

/*
 * Created by rrzaniolo on 08/05/18.
 * Copyright © 2018 rrzaniolo. All rights reserved.
 */

import rrzaniolo.testandroidsantander.network.models.response.CellResponse;

/**
 * A base cell from which all Cell in the form will extend.
 * */
public class Cell {
    //region --- Constant
    public Integer TYPE_EMAIL = 3;
    //endregion

    //region --- Variables
    private Integer id;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    private Integer type;
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }

    private Integer show;
    public Integer getShow() {
        return show;
    }
    public void setShow(Integer show) {
        this.show = show;
    }

    private Double topSpacing;
    public Double getTopSpacing() {
        return topSpacing;
    }
    public void setTopSpacing(Double topSpacing) {
        this.topSpacing = topSpacing;
    }

    private String message;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    private Boolean required;
    public Boolean getRequired() {
        return required;
    }
    public void setRequired(Boolean required) {
        this.required = required;
    }

    private Boolean hidden;
    public Boolean getHidden() {
        return hidden;
    }
    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }
    //endregion

    //region --- Constructor
    public Cell() { }

    public Cell(Integer id, Integer type, Integer show, Double topSpacing, String message, Boolean required, Boolean hidden) {
        this.id = id;
        this.type = type;
        this.show = show;
        this.topSpacing = topSpacing;
        this.message = message;
        this.required = required;
        this.hidden = hidden;
    }

    public Cell(CellResponse cellResponse){
        this.id = cellResponse.getId()                  != null ? cellResponse.getId()          : -1;
        this.type = cellResponse.getType()              != null ? cellResponse.getType()        : -1;
        this.show = cellResponse.getShow()              != null ? cellResponse.getShow()        : -1;
        this.topSpacing = cellResponse.getTopSpacing()  != null ? cellResponse.getTopSpacing()  : 0;
        this.message = cellResponse.getMessage()        != null ? cellResponse.getMessage()     : "";
        this.required = cellResponse.getRequired()      != null ? cellResponse.getRequired()    : false;
        this.hidden = cellResponse.getHidden()          != null ? cellResponse.getHidden()      : false;
    }
    //endregion
}
