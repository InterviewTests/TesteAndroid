package br.com.testeandroid.feature;

import android.util.Log;

import br.com.testeandroid.model.Tela;
import br.com.testeandroid.network.NetworkUrl;
import br.com.testeandroid.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvestimentoPresenterImpl implements InvestimentoPresenter {


    private InvestimentoView mainView;

    public InvestimentoPresenterImpl(InvestimentoView mainView) {
        this.mainView = mainView;
    }


    @Override
    public void getInvestimentoArrayList() {

        mainView.showProgress();

        NetworkUrl service = RetrofitInstance.getRetrofitInstance().create(NetworkUrl.class);

        Call<Tela> call = service.getTelaInvestimento();

        call.enqueue(new Callback<Tela>() {

            @Override
            public void onResponse(Call<Tela> call, Response<Tela> response) {

                mainView.setScreen(response.body().getScreen());
                mainView.setInfoInvestimento(response.body().getScreen().getMoreInfo());
                mainView.setRecycleViewAdapter(response.body().getScreen().getInfo(), response.body().getScreen().getDownInfo());

                mainView.finishProgress();
            }

            @Override
            public void onFailure(Call<Tela> call, Throwable t) {
                mainView.finishProgress();
                mainView.ErroLoading();
            }
        });
    }
}
