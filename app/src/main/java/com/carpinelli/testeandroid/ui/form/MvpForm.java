package com.carpinelli.testeandroid.ui.form;

import com.carpinelli.testeandroid.model.Cell;
import com.carpinelli.testeandroid.ui.base.Mvp;

import java.util.List;

public interface MvpForm {

    interface View extends Mvp.View {

        void onCellsReady(List<Cell> cells);

        void onFormSucces();

        void onFormFail();

    }

    interface Presenter extends Mvp.Presenter {

        void onStart();

        android.view.View.OnClickListener onSend();

    }

}
