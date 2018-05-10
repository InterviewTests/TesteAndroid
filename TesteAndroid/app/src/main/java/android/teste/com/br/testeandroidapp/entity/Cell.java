package android.teste.com.br.testeandroidapp.entity;

/**
 * Representação das células baixadas
 * @author Gabriela Santos
 */

public class Cell {

    private Integer id;
    private Type type;
    private String message;
    private TypeField typefield;
    private Boolean hidden;
    private Integer topSpacing;
    private Integer show;
    private Boolean required;

    public Integer getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public TypeField getTypefield() {
        return typefield;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public Integer getTopSpacing() {
        return topSpacing;
    }

    public Integer getShow() {
        return show;
    }

    public Boolean getRequired() {
        return required;
    }
}
