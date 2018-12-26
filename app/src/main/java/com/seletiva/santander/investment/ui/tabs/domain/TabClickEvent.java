package com.seletiva.santander.investment.ui.tabs.domain;

/**
 * Classe utilizada para envio/recebimento de eventos de clique da `tabBar`.
 */
public class TabClickEvent {
    private String title;

    public TabClickEvent(String title) {
        this.title = title;
    }

    /**
     * Titulo a ser utilizado na toolbar/actionbar
     * @return a ser utilizado na toolbar/actionbar
     */
    public String getTitle() {
        return title;
    }
}
