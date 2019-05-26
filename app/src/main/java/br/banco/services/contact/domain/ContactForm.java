package br.banco.services.contact.domain;

public class ContactForm {
    
    int Id; //1,
    int Type; // 2,
    String Message;  //
    String Typefield; //null,
    Boolean Hidden; //false,
    Double TopSpacing; // 60.0,
    String Show; //null,
    Boolean Required; //false

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getTypefield() {
        return Typefield;
    }

    public void setTypefield(String typefield) {
        Typefield = typefield;
    }

    public Boolean getHidden() {
        return Hidden;
    }

    public void setHidden(Boolean hidden) {
        Hidden = hidden;
    }

    public Double getTopSpacing() {
        return TopSpacing;
    }

    public void setTopSpacing(Double topSpacing) {
        TopSpacing = topSpacing;
    }

    public String getShow() {
        return Show;
    }

    public void setShow(String show) {
        Show = show;
    }

    public Boolean getRequired() {
        return Required;
    }

    public void setRequired(Boolean required) {
        Required = required;
    }











    @Override
    public String toString() {
        return "ContactForm{" +
                "Id=" + Id +
                ", Type=" + Type +
                ", Message='" + Message + '\'' +
                ", Typefield='" + Typefield + '\'' +
                ", Hidden=" + Hidden +
                ", TopSpacing=" + TopSpacing +
                ", Show='" + Show + '\'' +
                ", Required=" + Required +
                '}';
    }






}
