package br.com.accenture.santander.wallacebaldenebre.ui.main.fragments.investment;

import java.util.HashMap;

import br.com.accenture.santander.wallacebaldenebre.model.Screen;
import br.com.accenture.santander.wallacebaldenebre.ui.base.MvpPresenter;
import br.com.accenture.santander.wallacebaldenebre.ui.base.MvpView;

public interface InvestmentContract {
    interface View extends MvpView {
        void casts(android.view.View view);

        void setUp(android.view.View view);
    }

    interface Presenter<V extends MvpView> extends MvpPresenter<V> {
        void showDataFromServer(InvestmentFragment infra, final InvestmentCallback<HashMap<String, Screen>> callback);
    }
}
