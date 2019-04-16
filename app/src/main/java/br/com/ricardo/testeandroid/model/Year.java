package br.com.ricardo.testeandroid.model;

import com.google.gson.annotations.SerializedName;

public class Year {

    private float fund;

    @SerializedName("CDI")
    private float cdi;

    public Year(float fund, float cdi) {
        this.fund = fund;
        this.cdi = cdi;
    }

    public float getFund() {
        return fund;
    }

    public void setFund(float fund) {
        this.fund = fund;
    }

    public float getCdi() {
        return cdi;
    }

    public void setCdi(float cdi) {
        this.cdi = cdi;
    }
}
