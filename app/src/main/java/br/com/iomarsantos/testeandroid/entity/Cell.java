package br.com.iomarsantos.testeandroid.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cell implements Serializable {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("message")
    private String message;

    @TypeField
    @JsonProperty("typefield")
    private String typefield;

    @JsonProperty("hidden")
    private Boolean hidden;

    @JsonProperty("topSpacing")
    private Float topSpacing;

    @JsonProperty("show")
    private Integer show;

    @JsonProperty("required")
    private Boolean required;

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("type")
    public Integer getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(Integer type) {
        this.type = type;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("typefield")
    public String getTypefield() {
        return typefield;
    }

    @JsonProperty("typefield")
    public void setTypefield(String typefield) {
        this.typefield = typefield;
    }

    @JsonProperty("hidden")
    public Boolean getHidden() {
        return hidden;
    }

    @JsonProperty("hidden")
    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    @JsonProperty("topSpacing")
    public Float getTopSpacing() {
        return topSpacing;
    }

    @JsonProperty("topSpacing")
    public void setTopSpacing(Float topSpacing) {
        this.topSpacing = topSpacing;
    }

    @JsonProperty("show")
    public Integer getShow() {
        return show;
    }

    @JsonProperty("show")
    public void setShow(Integer show) {
        this.show = show;
    }

    @JsonProperty("required")
    public Boolean getRequired() {
        return required;
    }

    @JsonProperty("required")
    public void setRequired(Boolean required) {
        this.required = required;
    }

}
