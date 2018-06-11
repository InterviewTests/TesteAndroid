package info.dafle.testeandroid.mvp.contato;

import android.util.Log;

import info.dafle.testeandroid.model.Cell;
import info.dafle.testeandroid.model.ContatoResponse;
import info.dafle.testeandroid.service.ApiConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContatoModel implements ContatoContract.Model {

    private static final String TAG = ContatoModel.class.getSimpleName();

    @Override
    public void getCells(final LoadCellsCallback loadCellsCallback) {

        ApiConfig.getService().getCells().enqueue(new Callback<ContatoResponse>() {
            @Override
            public void onResponse(Call<ContatoResponse> call, Response<ContatoResponse> response) {
                loadCellsCallback.onCellsLoaded(response.body().getCells());
            }

            @Override
            public void onFailure(Call<ContatoResponse> call, Throwable t) {
                String error = "Error "+t.getMessage() + " " + t.getLocalizedMessage();
                Log.e(TAG, error);
                loadCellsCallback.onErrorFetchData("Error ");
            }
        });
    }
}
