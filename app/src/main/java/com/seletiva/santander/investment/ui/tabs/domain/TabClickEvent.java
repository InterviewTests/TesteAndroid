package com.seletiva.santander.investment.ui.tabs.domain;

public class TabClickEvent {
    private String title;

    public TabClickEvent(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
