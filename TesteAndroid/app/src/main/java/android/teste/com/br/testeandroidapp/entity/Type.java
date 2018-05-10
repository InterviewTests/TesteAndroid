package android.teste.com.br.testeandroidapp.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Representação dos tipos de campos
 * @author Gabriela Santos
 */

public enum Type {
    @SerializedName("1") FIELD,
    @SerializedName("2") TEXT,
    @SerializedName("3") IMAGE,
    @SerializedName("4") CHECKBOX,
    @SerializedName("5") SEND

}
