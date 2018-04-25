package com.aline.teste.eventbus;

import com.aline.teste.Models.Screen;


public class EventFund {

    private Screen screen = null;
    public EventFund(Screen screen) {
        this.screen = screen;
    }


    public Screen getScreenFund() {
        return screen;
    }
}
