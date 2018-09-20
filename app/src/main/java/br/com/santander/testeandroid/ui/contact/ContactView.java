package br.com.santander.testeandroid.ui.contact;

import android.widget.EditText;

import java.util.List;

import br.com.santander.testeandroid.ui.contact.domain.models.Cell;

public interface ContactView {
    void prepareToolbar();
    void showProgressBar();
    void hideProgressBar();
    void loadInformationSuccess(List<Cell> cellsList);
    void loadInformationFailed();
    void showSuccessMessage();
}
