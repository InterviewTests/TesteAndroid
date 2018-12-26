package com.seletiva.santander.investment.ui.form.domain.model;

/**
 * Classe utilizada para envio/recebimento de eventos de clique no botão 'enviar'.
 */
public class SendButtonClickEvent {
    public static SendButtonClickEvent newClickEvent() {
        return new SendButtonClickEvent();
    }
}
