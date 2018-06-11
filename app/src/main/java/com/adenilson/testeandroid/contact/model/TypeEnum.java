package com.adenilson.testeandroid.contact.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

public enum TypeEnum {
    @SerializedName("1")
    FIELD(1),
    @SerializedName("2")
    TEXT(2),
    @SerializedName("3")
    IMAGE(3),
    @SerializedName("4")
    CHECKBOX(4),
    @SerializedName("5")
    SEND(5);

    public final int type;

    TypeEnum(int i) {
        this.type = i;
    }
}
