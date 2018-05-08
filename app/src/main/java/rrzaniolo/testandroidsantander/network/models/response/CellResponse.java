package rrzaniolo.testandroidsantander.network.models.response;

/*
 * Created by rrzaniolo on 08/05/18.
 * Copyright © 2018 rrzaniolo. All rights reserved.
 */

import com.google.gson.annotations.SerializedName;

/**
 * This is the Model for the form.
 * */
public class CellResponse {
    //region --- Variables
    @SerializedName("id")
    private Integer id;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @SerializedName("type")
    private Integer type;
    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }

    @SerializedName("show")
    private Integer show;
    public Integer getShow() {
        return show;
    }
    public void setShow(Integer show) {
        this.show = show;
    }

    @SerializedName("topSpacing")
    private Double topSpacing;
    public Double getTopSpacing() {
        return topSpacing;
    }
    public void setTopSpacing(Double topSpacing) {
        this.topSpacing = topSpacing;
    }

    @SerializedName("message")
    private String message;
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    @SerializedName("required")
    private Boolean required;
    public Boolean getRequired() {
        return required;
    }
    public void setRequired(Boolean required) {
        this.required = required;
    }

    @SerializedName("hidden")
    private Boolean hidden;
    public Boolean getHidden() {
        return hidden;
    }
    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    @SerializedName("typefield")
    private Object typeField;
    public Object getTypeField() {
        return typeField;
    }
    public void setTypeField(Object typeField) {
        this.typeField = typeField;
    }
    //endregion

    //region --- Constructors
    public CellResponse() { }

    public CellResponse(Integer id, Integer type, Integer show, Double topSpacing, String message, Boolean required, Boolean hidden, Object typeField) {
        this.id = id;
        this.type = type;
        this.show = show;
        this.topSpacing = topSpacing;
        this.message = message;
        this.required = required;
        this.hidden = hidden;
        this.typeField = typeField;
    }
    //endregion
}
