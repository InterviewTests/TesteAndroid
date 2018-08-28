package br.com.santander.testeandroid.contact;


import br.com.santander.testeandroid.contact.model.CellsResponse;

public interface ContactContract {

    void configureUI(CellsResponse response);

    void showError();

    void hideError();

    void clearForm();

    void showSuccess();

    void hideSuccess();

    void showLoading();

    void hideLoading();
}
