package br.com.santander.testeandroid.ui.contact.domain.models;

import com.google.gson.annotations.SerializedName;

public enum TypeField {
    @SerializedName("1")
    TEXT,
    @SerializedName("telnumber")
    TEL_NUMBER,
    @SerializedName("3")
    EMAIL
}
