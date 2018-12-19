package com.avanade.santander.fundos.domain.model;

import com.google.gson.annotations.Expose;

public class Fundos {

    @Expose
    private final Screen screen;

    public Fundos(Screen screen) {
        this.screen = screen;
    }

    public Screen getScreen() {
        return screen;
    }
}
