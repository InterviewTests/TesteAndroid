package br.com.iomarsantos.testeandroid.ui.fundo.investimentos;

import br.com.iomarsantos.testeandroid.ui.base.Presenter;

public interface InvestimentoBasePresenter<V extends InvestimentoView> extends Presenter<V> {
    void findAllFundApiCall();
}
