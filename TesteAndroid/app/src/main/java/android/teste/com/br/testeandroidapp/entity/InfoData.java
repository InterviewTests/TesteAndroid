package android.teste.com.br.testeandroidapp.entity;

import com.google.gson.annotations.SerializedName;

public class InfoData {
    private Float fund;

    @SerializedName("CDI")
    private Float cdi;

    public Float getFund() {
        return fund;
    }

    public Float getCdi() {
        return cdi;
    }
}
