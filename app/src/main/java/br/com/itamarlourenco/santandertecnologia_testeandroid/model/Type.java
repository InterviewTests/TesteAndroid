package br.com.itamarlourenco.santandertecnologia_testeandroid.model;

import com.google.gson.annotations.SerializedName;

public enum Type {
    title(-1),

    @SerializedName("1")
    field(1),

    @SerializedName("2")
    text(2),

    @SerializedName("3")
    image(3),

    @SerializedName("4")
    checkbox(4),

    @SerializedName("5")
    send(5);

    private int value;

    Type(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
