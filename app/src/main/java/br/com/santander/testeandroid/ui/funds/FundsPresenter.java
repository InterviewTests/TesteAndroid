package br.com.santander.testeandroid.ui.funds;

import br.com.santander.testeandroid.ui.funds.domain.models.Funds;
import br.com.santander.testeandroid.ui.funds.domain.usecases.GetFunds;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FundsPresenter {
    private FundsView view;

    public FundsPresenter(FundsView view) {
        this.view = view;
    }

    public void loadScreenInfo() {
        view.prepareToolbar();
        view.showProgressBar();

        GetFunds.getFunds(new Callback<Funds>() {
            @Override
            public void onResponse(Call<Funds> call, Response<Funds> response) {
                if (response.isSuccessful()) {
                    Funds funds = response.body();
                    if (funds != null) {
                        view.loadInformationSuccess(funds.getScreen());
                        return;
                    }

                    view.loadInfomationFailed();
                } else {
                    view.loadInfomationFailed();
                }
            }

            @Override
            public void onFailure(Call<Funds> call, Throwable t) {
                view.loadInfomationFailed();
            }
        });
    }
}
