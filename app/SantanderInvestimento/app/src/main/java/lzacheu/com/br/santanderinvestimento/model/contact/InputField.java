package lzacheu.com.br.santanderinvestimento.model.contact;

import com.google.gson.annotations.SerializedName;

/**
 * Created by luiszacheu on 6/16/18.
 */

public class InputField {

    private Integer id;

    private Integer type;

    private String message;

    @SerializedName("typefield")
    private Object typeTield;

    private Boolean hidden;

    private Integer topSpacing;

    private Object show;

    private Boolean required;

    public InputField() {
    }

    public InputField(Integer id, Integer type, String message, Object typeTield, Boolean hidden, Integer topSpacing, Object show, Boolean required) {
        this.id = id;
        this.type = type;
        this.message = message;
        this.typeTield = typeTield;
        this.hidden = hidden;
        this.topSpacing = topSpacing;
        this.show = show;
        this.required = required;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getTypeTield() {
        return typeTield;
    }

    public void setTypeTield(Object typeTield) {
        this.typeTield = typeTield;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Integer getTopSpacing() {
        return topSpacing;
    }

    public void setTopSpacing(Integer topSpacing) {
        this.topSpacing = topSpacing;
    }

    public Object getShow() {
        return show;
    }

    public void setShow(Object show) {
        this.show = show;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

    @Override
    public String toString() {
        return "InputField{" +
                "id=" + id +
                ", type=" + type +
                ", message='" + message + '\'' +
                ", typeTield=" + typeTield +
                ", hidden=" + hidden +
                ", topSpacing=" + topSpacing +
                ", show=" + show +
                ", required=" + required +
                '}';
    }
}
