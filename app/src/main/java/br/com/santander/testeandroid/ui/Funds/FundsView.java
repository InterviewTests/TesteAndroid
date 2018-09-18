package br.com.santander.testeandroid.ui.Funds;

import br.com.santander.testeandroid.ui.Funds.domain.Models.FundsScreen;

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
