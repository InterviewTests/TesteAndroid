package br.banco.services.contact.domain;

public class ProdutoV1 {


    String id;
    String type;
    String message;
    String typefield;
    String hidden;
    String topSpacing;
    String show;
    String required;


    public ProdutoV1(){


    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTypefield() {
        return typefield;
    }

    public void setTypefield(String typefield) {
        this.typefield = typefield;
    }

    public String getHidden() {
        return hidden;
    }

    public void setHidden(String hidden) {
        this.hidden = hidden;
    }

    public String getTopSpacing() {
        return topSpacing;
    }

    public void setTopSpacing(String topSpacing) {
        this.topSpacing = topSpacing;
    }

    public String getShow() {
        return show;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }


    @Override
    public String toString() {
        return "Produto{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", message='" + message + '\'' +
                ", typefield='" + typefield + '\'' +
                ", hidden='" + hidden + '\'' +
                ", topSpacing='" + topSpacing + '\'' +
                ", show='" + show + '\'' +
                ", required='" + required + '\'' +
                '}';
    }
}
