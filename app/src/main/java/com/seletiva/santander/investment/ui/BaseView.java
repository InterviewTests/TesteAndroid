package com.seletiva.santander.investment.ui;

public interface BaseView {
    void showProgressDialog();
    void hideProgressDialog();

    /**
     * A realizacao deste metodo devera recuperar o valor do resourceId (string)
     * a utilizá-la para exibicao de mensagem customizada
     * @param resId ResourceId da string a ser recuperada
     */
    void showMessage(int resId);
}