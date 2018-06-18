package lzacheu.com.br.santanderinvestimento.data;

import android.util.Log;

import lzacheu.com.br.santanderinvestimento.data.remote.ApiClient;
import lzacheu.com.br.santanderinvestimento.data.remote.contact.ContactResponse;
import lzacheu.com.br.santanderinvestimento.data.remote.contact.ContactService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by luiszacheu on 6/16/18.
 */

public class ContactRepository implements ContactDataSource {

    private static final String LOG_TAG = ContactRepository.class.getSimpleName();
    private ContactService service;


    public ContactRepository() {
        this.service = ApiClient.getRetrofitInstance().create(ContactService.class);
    }

    @Override
    public void getCells(final LoadCellsCallback callback) {
        Call<ContactResponse> contactResponseCall = service.getContactFields();
        contactResponseCall.enqueue(new Callback<ContactResponse>() {
            @Override
            public void onResponse(Call<ContactResponse> call, Response<ContactResponse> response) {
                callback.onCellsLoaded(response.body().getInputFields());
            }

            @Override
            public void onFailure(Call<ContactResponse> call, Throwable t) {
                Log.e(LOG_TAG, " Ocorreu alguma falha" );
            }
        });
    }
}
