package com.adenilson.testeandroid.contact.ui.custom;

import android.view.ViewGroup;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

public interface CustomViewInterface {

    void setTopMargin(ViewGroup.MarginLayoutParams layoutParams);

    void setMessage(String message);

    void setVisibility();

    boolean isRequired();

    void setRequired(boolean isRequired);

    boolean checkError();

    void clearField();
}
