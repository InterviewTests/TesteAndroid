package com.seletiva.santander.investment.ui.investments;

import com.seletiva.santander.investment.ui.BasePresenter;
import com.seletiva.santander.investment.ui.BaseView;
import com.seletiva.santander.investment.ui.investments.adapters.InvestmentsAdapter;
import com.seletiva.santander.investment.ui.investments.domain.MoreInfo;

/**
 * Contrato View/Presenter para investimentos
 */
public interface Investments {
    interface View extends BaseView {
        /**
         * Configura o header
         *
         * @param title Titulo
         * @param fundName Nome do fundo de investimentos
         * @param whatIs Label `o que é?`
         * @param definition Descricao do fundo
         */
        void configureHeader(String title, String fundName, String whatIs, String definition);

        /**
         * Configura view (bar) de grau de risco
         *
         * @param riskTitle Titulo
         * @param risk grau de risco
         */
        void configureRiskBar(String riskTitle, int risk);

        /**
         * Configura view de CDI mes/ano/12meses
         *
         * @param infoTitle Titulo
         * @param moreInfo modelo contendo mes/ano/12meses
         */
        void configureInfo(String infoTitle, MoreInfo moreInfo);

        /**
         * Configura o RecyclerView com o adapter
         *
         * @param adapter adapter utilizado para configuracao da lista
         */
        void configureInvestmentsList(InvestmentsAdapter adapter);
    }

    interface Presenter extends BasePresenter {
        /**
         * Carrega informacoes de investimentos (API funds)
         */
        void loadFunds();
    }
}
