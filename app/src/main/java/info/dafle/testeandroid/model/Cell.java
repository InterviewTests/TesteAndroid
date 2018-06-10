package info.dafle.testeandroid.model;

public class Cell {

    private int id;
    private int type;
    private String message;
    private Object typefield;
    private boolean hidden;
    private int topSpacing;
    private int show;
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

    public Object getTypefield() {
        return typefield;
    }

    public void setTypefield(Object typefield) {
        this.typefield = typefield;
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

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}
