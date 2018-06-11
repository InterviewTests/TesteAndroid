package com.adenilson.testeandroid.contact;

import com.adenilson.testeandroid.base.BaseView;
import com.adenilson.testeandroid.contact.model.Cell;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

public interface ContactView extends BaseView {

    void addTextView(Cell cell);

    void addEditText(Cell cell);

    void addCheckbox(Cell cell);

    void addButton(Cell cell);

    void addImage(Cell cell);

    void showSuccessScreen();

    void setSavedValues();
}
