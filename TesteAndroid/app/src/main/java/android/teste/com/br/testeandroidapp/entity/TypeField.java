package android.teste.com.br.testeandroidapp.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Representação dos tipos de dados dos campos
 * @author Gabriela Santos
 */

public enum TypeField {
    @SerializedName("1") TEXT,
    @SerializedName(value = "2", alternate = {"telnumber"}) TEL_NUMBER,
    @SerializedName("3") EMAIL
}