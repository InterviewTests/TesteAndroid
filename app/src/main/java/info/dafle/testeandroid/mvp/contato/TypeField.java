package info.dafle.testeandroid.mvp.contato;

import android.support.annotation.NonNull;

enum TypeField {
    text(1.0),
    telNumber("telnumber"),
    email(3.0);

    Object typeField;
    TypeField(Object object) {
        this.typeField = object;
    }

    @NonNull
    public static TypeField getFromObject(Object val) {
        for (TypeField typeField: values()) {
            if (typeField.typeField.equals(val)) {
                return  typeField;
            }
        }
        return text;
    }
}