package br.com.itamarlourenco.santandertecnologia_testeandroid.model;

import com.google.gson.annotations.SerializedName;

public enum TypeField {
    @SerializedName("1")
    text(1),

    @SerializedName("2")
    telNumber(2),

    @SerializedName("3")
    email(3);

    private int value;

    TypeField(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
