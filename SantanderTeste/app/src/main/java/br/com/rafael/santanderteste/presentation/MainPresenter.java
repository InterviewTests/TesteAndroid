package br.com.rafael.santanderteste.presentation;

import org.jetbrains.annotations.NotNull;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mainView;

    /**
     * Para ativacao do fragment de Investimentos
     */
    @Override
    public void initInvestimentFragment() {
        mainView.showInvestimentFragment();
    }

    /**
     * Para ativacao do fragment de Contato
     */
    @Override
    public void initContactFragment() {
        mainView.showContactFragment();
    }

    /**
     * Configura a View implementada
     * @param view
     */
    @Override
    public void setView(@NotNull MainContract.View view) {
        this.mainView = view;
    }
}
