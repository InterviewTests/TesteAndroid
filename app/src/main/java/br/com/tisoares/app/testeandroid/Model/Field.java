package br.com.tisoares.app.testeandroid.Model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


/**
 * Created by TIAGO SOARES on 12/12/2018.
 */
public class Field {

    public Field(JSONObject jsonObject) throws JSONException, UnsupportedEncodingException {
        this.jsonObject = jsonObject;
        this.createByJson();
    }

    private JSONObject jsonObject;

    // Id do field
    private int id;

    //  "message": mensagem que vai aparecer na label para type = text ou placeholder para field;
    private String message;

    //hidden": indica se o campo está visível;
    private boolean hidden;

    //topSpacing": espaçamento entre o topo da célula e o topo da label/field/checkbox;
    private int topSpacing;

    //show": indica o campo que será exibido quando este campo for selecionado. No caso é o id do campo a ser exibido.
    private int show;

    //risk": pode ser um int de 1 a 5
    private int risk;

    //"type": tipo da célula;
    private CompType type;

    //TypeFieldComp": tipo do field a ser exibido, para exibir corretamente a validação daquele campo.
    private TypeFieldComp typefield;

    private boolean required;

    public enum CompType {
        field(1),
        text(2),
        image(3),
        checkbox(4),
        send(5);

        private final int valor;
        CompType(int valorType){
            valor = valorType;
        }
        public int getValor(){
            return valor;
        }

        static CompType fromValue(int value) {
            for (CompType my: CompType.values()) {
                if (my.valor == value) {
                    return my;
                }
            }
            return null;
        }
    }

    //TypeFieldComp": tipo do field a ser exibido, para exibir corretamente a validação daquele campo.
    public enum TypeFieldComp {
        none(-1),
        text(1),
        telnumber(2),
        email(3);

        private final int valor;

        TypeFieldComp(int valorTypeField){
            valor = valorTypeField;
        }
        public int getValor(){
            return valor;
        }

        static TypeFieldComp fromValue(int value) {
            for (TypeFieldComp my: TypeFieldComp.values()) {
                if (my.valor == value) {
                    return my;
                }
            }
            return TypeFieldComp.none;
        }
        static TypeFieldComp valueOfName(String name){
            try{
                return valueOf(name.toLowerCase());
            }catch (Exception e){
                return TypeFieldComp.none;
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public int getRisk() {
        return risk;
    }

    public void setRisk(int risk) {
        if (risk >= 1 && risk <= 3) {
            this.risk = risk;
        } else {
            throw new IllegalArgumentException("risk não é um valor permitido");
        }
    }

    public CompType getType() {
        return type;
    }

    public void setType(CompType type) {
        this.type = type;
    }

    public TypeFieldComp getTypefield() {
        return typefield;
    }

    public void setTypefield(TypeFieldComp typefield) {
        this.typefield = typefield;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    /*
            Define os valores dos atributos da classe
    */
    private void createByJson() throws JSONException, UnsupportedEncodingException {
        /*
            "id": 1,
			"type": 2,
			"message": "Olá, primeiro se apresente com o seu nome:",
			"typefield": null,
			"hidden": false,
			"topSpacing": 60.0,
			"show": null,
			"required": false
        */
        setId(jsonObject.getInt("id"));
        type = CompType.fromValue(jsonObject.getInt("type"));
        message = new String(jsonObject.getString("message").getBytes("ISO-8859-1"), "UTF-8");

        // verifico se o typefield é none
        if (jsonObject.isNull("typefield"))
            typefield = TypeFieldComp.none;
        else {
            try {
                typefield = TypeFieldComp.fromValue(jsonObject.getInt("typefield"));
            }catch (JSONException e){
                typefield = TypeFieldComp.valueOfName(jsonObject.getString("typefield"));
            }
        }
        hidden = jsonObject.getBoolean("hidden");
        topSpacing = jsonObject.getInt("topSpacing");
        if (jsonObject.isNull("show"))
            show = -1;
        else
            show = jsonObject.getInt("show");
        required = jsonObject.getBoolean("required");
        if (jsonObject.has("risk"))
            setRisk(jsonObject.getInt("risk"));
    }
}
