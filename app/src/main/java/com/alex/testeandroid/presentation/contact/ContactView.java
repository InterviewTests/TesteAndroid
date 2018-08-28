package com.alex.testeandroid.presentation.contact;

import com.alex.testeandroid.data.entities.contact.Cell;
import com.alex.testeandroid.presentation.BaseView;

import java.util.List;

/**
 * Created by Alex on 27/08/18.
 */
public interface ContactView extends BaseView {

    void showProgress(boolean show);

    void showMessageErrorRequest();

    void setupCells(List<Cell> cells);

    void showErrorName();

    void showErrorEmail();

    void showErrorPhone();
}
