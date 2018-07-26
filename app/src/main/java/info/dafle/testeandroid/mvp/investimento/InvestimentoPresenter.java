package info.dafle.testeandroid.mvp.investimento;

import android.support.annotation.NonNull;

import info.dafle.testeandroid.model.Fund;
import info.dafle.testeandroid.service.ApiConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvestimentoPresenter implements InvestimentoContract.Presenter {

    private InvestimentoContract.View mView;

    InvestimentoPresenter(InvestimentoContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

        mView.showProgress();
        ApiConfig.getService().getFund().enqueue(new Callback<Fund>() {
            @Override
            public void onResponse(@NonNull Call<Fund> call, @NonNull Response<Fund> response) {
                mView.buildLayout(response.body());
                mView.hideProgress();
            }

            @Override
            public void onFailure(@NonNull Call<Fund> call, @NonNull Throwable t) {
                mView.showMessage(t.getMessage());
            }
        });
    }
}
