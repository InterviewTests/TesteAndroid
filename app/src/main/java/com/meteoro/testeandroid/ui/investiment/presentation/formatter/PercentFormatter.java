package com.meteoro.testeandroid.ui.investiment.presentation.formatter;

import android.content.Context;

import com.meteoro.testeandroid.R;

import javax.inject.Inject;

public class PercentFormatter {

    private final Context context;

    @Inject
    public PercentFormatter(Context context) {
        this.context = context;
    }

    public String format(double value) {
        return String.format(context.getString(R.string.fragment_investiment_percent_format), value);
    }
}
