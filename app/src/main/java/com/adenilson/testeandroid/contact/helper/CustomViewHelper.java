package com.adenilson.testeandroid.contact.helper;

import com.adenilson.testeandroid.contact.ui.custom.CustomEditText;
import com.adenilson.testeandroid.contact.ui.custom.CustomViewInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 11/06/2018
 */

public class CustomViewHelper {
    List<CustomViewInterface> mViews;

    public CustomViewHelper() {
        mViews = new ArrayList<>();
    }

    public void addCustomView(CustomViewInterface view) {
        mViews.add(view);
    }

    public boolean checkError() {
        boolean hasError = false;

        for (CustomViewInterface view : mViews) {
            if (view.checkError()) {
                hasError = true;
            }
        }
        return hasError;
    }

    public void clearFields() {
        for (CustomViewInterface view : mViews) {
            view.clearField();
        }
    }

    public List<String> getStrings() {
        ArrayList<String> strings = new ArrayList<>();
        for (CustomViewInterface customView : mViews) {
            if (customView instanceof CustomEditText) {
                strings.add((((CustomEditText) customView).getText().toString()));

            }
        }
        return strings;
    }

    public void setStrings(List<String> strings) {
        int indexString = 0;
        for (CustomViewInterface customView : mViews) {
            if (customView instanceof CustomEditText) {
                ((CustomEditText) customView).setText(strings.get(indexString));
                indexString++;
            }
        }
    }
}
