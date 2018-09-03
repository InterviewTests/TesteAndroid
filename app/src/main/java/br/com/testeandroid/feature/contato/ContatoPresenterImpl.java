package br.com.testeandroid.feature.contato;

import br.com.testeandroid.model.CellsResponse;
import br.com.testeandroid.network.NetworkUrl;
import br.com.testeandroid.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContatoPresenterImpl implements ContatoPresenter {


    private ContatoView mainView;

    public ContatoPresenterImpl(ContatoView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void getCellArrayList() {
        mainView.showProgress();

        NetworkUrl service = RetrofitInstance.getRetrofitInstance().create(NetworkUrl.class);

        Call<CellsResponse> call = service.getCells();

        call.enqueue(new Callback<CellsResponse>() {
            @Override
            public void onResponse(Call<CellsResponse> call, Response<CellsResponse> response) {
                mainView.ConfigureCells(response.body().getCells());
                mainView.finishProgress();
            }

            @Override
            public void onFailure(Call<CellsResponse> call, Throwable t) {
                mainView.finishProgress();
                mainView.ErroLoading();
            }
        });
    }
}
