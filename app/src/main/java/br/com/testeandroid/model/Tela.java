package br.com.testeandroid.model;

import com.google.gson.annotations.SerializedName;

public class Tela {
    @SerializedName("screen")
    private Screen screen;

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }
}
