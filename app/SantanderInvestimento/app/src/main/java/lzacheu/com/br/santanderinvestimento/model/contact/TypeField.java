package lzacheu.com.br.santanderinvestimento.model.contact;

/**
 * Created by luiszacheu on 6/17/18.
 */

public enum TypeField {
    text(1),
    telNumber("telnumber"),
    email(3);

    private Object value;

    TypeField(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }
}
