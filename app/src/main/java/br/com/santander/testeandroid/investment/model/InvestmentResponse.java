package br.com.santander.testeandroid.investment.model;

import com.google.gson.annotations.SerializedName;

public class InvestmentResponse {
    @SerializedName("screen")
    private Screen screen;

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }
}
