package lzacheu.com.br.santanderinvestimento.model.contact;

/**
 * Created by luiszacheu on 6/17/18.
 */

public enum TypeField {
    text(1),
    telNumber(2),
    email(3);

    private int value;

    TypeField(int value) {
        this.value = value;
    }
}
