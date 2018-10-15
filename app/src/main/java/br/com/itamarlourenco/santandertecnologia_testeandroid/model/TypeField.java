package br.com.itamarlourenco.santandertecnologia_testeandroid.model;

import com.google.gson.annotations.SerializedName;

public enum TypeField {
    @SerializedName("1")
    text(1),

    @SerializedName(value="telnumber", alternate={"2"})
    telNumber(2, "(##) #####-####"),

    @SerializedName("3")
    email(3);

    private int value;
    private String format;

    TypeField(int value, String format) {
        this.value = value;
        this.format = format;
    }

    TypeField(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getFormat() {
        return format;
    }
}
