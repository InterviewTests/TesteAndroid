package br.com.santander.testeandroid.ui.funds;

import br.com.santander.testeandroid.ui.funds.domain.models.FundsScreen;

public interface FundsView {
    void prepareToolbar();
    void loadInformationSuccess(FundsScreen screen);
    void loadInfomationFailed();
    void showProgressBar();
    void hideProgressBar();
    void prepareRiskBar();
    void prepareMoreInfoTable();
    void prepareGeneralInfo();
    void prepareDownloadInfo();
}
