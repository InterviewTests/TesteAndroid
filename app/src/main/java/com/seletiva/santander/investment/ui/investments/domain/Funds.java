package com.seletiva.santander.investment.ui.investments.domain;

import com.google.gson.annotations.SerializedName;

public class Funds {
    @SerializedName("screen")
    private Screen screen;

    public Screen getScreen() {
        return screen;
    }
}
