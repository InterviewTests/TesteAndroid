package info.dafle.testeandroid.mvp.contato;

import android.support.annotation.NonNull;

enum Type {
    field(1),
    text(2),
    image(3),
    checkbox(4),
    send(5)
    ;

    public int type;
    Type(int type) {
        this.type = type;
    }

    @NonNull
    public static Type getFromInt(int val) {
        for (Type type: values()) {
            if (type.type == val) {
                return  type;
            }
        }
        return send;
    }
}
