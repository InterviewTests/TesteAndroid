package com.seletiva.santander.investment.ui.view;

import android.view.LayoutInflater;
import android.widget.LinearLayout;

public interface Form {
    /**
     * Retrieve the layout that will be used allocate the form
     * @return A valid linear layout
     */
    LinearLayout getFormContainer();

    /**
     * Get the layout inflater that will be used to inflate the form views
     * @return A valid layout inflater
     */
    LayoutInflater getInflater();
}
