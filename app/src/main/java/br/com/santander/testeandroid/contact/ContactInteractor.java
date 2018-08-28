package br.com.santander.testeandroid.contact;

import br.com.santander.testeandroid.api.APIServiceFactory;
import br.com.santander.testeandroid.contact.model.CellsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactInteractor {

    public interface ContactListener {
        void onGetCellsSuccess(CellsResponse response);

        void onGetCellsFailure();
    }

    public void getCells(final ContactListener listener) {
        APIServiceFactory.getInstance()
                .getCells()
                .enqueue(new Callback<CellsResponse>() {
                    @Override
                    public void onResponse(Call<CellsResponse> call, Response<CellsResponse> response) {
                        listener.onGetCellsSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<CellsResponse> call, Throwable t) {
                        listener.onGetCellsFailure();
                    }
                });

    }

}