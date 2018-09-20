package br.com.santander.testeandroid.ui.contact.domain.models;

import com.google.gson.annotations.SerializedName;

public enum Type {
    @SerializedName("1")
    FIELD,
    @SerializedName("2")
    TEXT,
    @SerializedName("3")
    IMAGE,
    @SerializedName("4")
    CHECKBOX,
    @SerializedName("5")
    SEND
}
