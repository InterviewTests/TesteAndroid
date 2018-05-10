package android.teste.com.br.testeandroidapp.entity;

import com.google.gson.annotations.SerializedName;

public class MoreInfo {
    private InfoData month;
    private InfoData year;

    @SerializedName("12months")
    private InfoData months;

    public InfoData getMonth() {
        return month;
    }

    public InfoData getYear() {
        return year;
    }

    public InfoData getMonths() {
        return months;
    }
}
