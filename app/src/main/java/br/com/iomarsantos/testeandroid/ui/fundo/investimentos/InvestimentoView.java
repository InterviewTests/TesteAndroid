package br.com.iomarsantos.testeandroid.ui.fundo.investimentos;

import br.com.iomarsantos.testeandroid.entity.InvestmentFund;
import br.com.iomarsantos.testeandroid.ui.base.BaseView;

public interface InvestimentoView extends BaseView {
    void updateVies(InvestmentFund investmentFund);
}
