package lzacheu.com.br.santanderinvestimento.model.contact;

/**
 * Created by luiszacheu on 6/17/18.
 */

public enum Type {
    field(1),
    text(2),
    image(3),
    checkbox(4),
    send(5);

    private int value;

    Type(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
