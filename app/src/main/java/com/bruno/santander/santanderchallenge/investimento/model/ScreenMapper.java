package com.bruno.santander.santanderchallenge.investimento.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScreenMapper {

    @JsonProperty("screen")
    private Screen screen;

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

}
