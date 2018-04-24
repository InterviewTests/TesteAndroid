package br.com.ibm.santander.wallacebaldenebre.ui.base;

public interface MvpContract {
    interface MvpView {
        void showProgress();

        void hideProgress();

        void hideKeyboard();

        void showData();
    }

    interface MvpPresenter<V extends MvpView> {
        void onAttach(V mvpView);
    }
}
