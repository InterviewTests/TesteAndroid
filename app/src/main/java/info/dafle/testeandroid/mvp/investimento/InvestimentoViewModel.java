package info.dafle.testeandroid.mvp.investimento;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import info.dafle.testeandroid.model.Fund;
import info.dafle.testeandroid.service.ApiConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvestimentoViewModel extends ViewModel implements InvestimentoContract.Presenter {

    private MutableLiveData<Fund> mFund;
    private InvestimentoContract.View mView;

    public LiveData<Fund> getFund() {
        if (mFund==null) {
            mFund = new MutableLiveData<>();
            loadFund();
        }
        return mFund;
    }

    @Override
    public void setView(InvestimentoContract.View view) {
        mView = view;
    }

    private void loadFund() {

        ApiConfig.getService().getFund().enqueue(new Callback<Fund>() {
            @Override
            public void onResponse(Call<Fund> call, Response<Fund> response) {
                mFund.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Fund> call, Throwable t) {
                mView.showMessage(t.getMessage());
            }
        });
    }
}
