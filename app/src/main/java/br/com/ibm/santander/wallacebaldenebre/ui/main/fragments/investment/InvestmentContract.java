package br.com.ibm.santander.wallacebaldenebre.ui.main.fragments.investment;

import java.util.HashMap;

import br.com.ibm.santander.wallacebaldenebre.model.Screen;
import br.com.ibm.santander.wallacebaldenebre.ui.base.MvpContract;

public interface InvestmentContract {
    interface View extends MvpContract.MvpView {
        void casts(android.view.View view);

        void setUp(android.view.View view);
    }

    interface Presenter<V extends MvpContract.MvpView> extends MvpContract.MvpPresenter<V> {
        void showDataFromServer(InvestmentFragment infra, final InvestmentCallback<HashMap<String, Screen>> callback);
    }
}
