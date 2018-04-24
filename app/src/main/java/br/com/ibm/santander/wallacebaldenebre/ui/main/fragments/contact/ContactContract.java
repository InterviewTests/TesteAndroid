package br.com.ibm.santander.wallacebaldenebre.ui.main.fragments.contact;

import java.util.HashMap;

import br.com.ibm.santander.wallacebaldenebre.model.Cell;
import br.com.ibm.santander.wallacebaldenebre.ui.base.MvpPresenter;
import br.com.ibm.santander.wallacebaldenebre.ui.base.MvpView;

public interface ContactContract {
    interface View extends MvpView {
        void casts(android.view.View view);

        void setUp(android.view.View view);
    }

    interface Presenter<V extends MvpView> extends MvpPresenter<V> {
        void showDataFormFields(ContactFragment confra, final ContactCallback<HashMap<String, Cell[]>> callback);
    }
}
