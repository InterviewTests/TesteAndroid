package br.com.santander.testeandroid.ui.Funds;

public class FundsPresenter {
    private FundsView view;

    public FundsPresenter(FundsView view) {
        this.view = view;
    }

    public void loadScreenInfo() {
        view.prepareToolbar();
    }
}
