package com.carpinelli.testeandroid.ui.form;

import com.carpinelli.testeandroid.model.form.Cell;
import com.carpinelli.testeandroid.di.Mvp;

import java.util.List;

public interface MvpForm {

    interface View extends Mvp.View {

        void onCellsReady(List<Cell> cells);

        void onSendForm();
    }

    interface Presenter extends Mvp.Presenter {

        void onStart();

        void onFormCompleted();
    }

}
