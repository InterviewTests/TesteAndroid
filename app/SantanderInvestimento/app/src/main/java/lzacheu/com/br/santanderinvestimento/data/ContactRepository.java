package lzacheu.com.br.santanderinvestimento.data;

import android.util.Log;

import lzacheu.com.br.santanderinvestimento.data.remote.ApiClient;
import lzacheu.com.br.santanderinvestimento.data.remote.contact.ContactResponse;
import lzacheu.com.br.santanderinvestimento.data.remote.contact.ContactService;
import lzacheu.com.br.santanderinvestimento.model.contact.InputField;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by luiszacheu on 6/16/18.
 */

public class ContactRepository extends FundRepository {

    private static final String TAG_LOG = ContactRepository.class.getSimpleName();
    private ContactService service;


    public ContactRepository() {
        this.service = ApiClient.getRetrofitInstance().create(ContactService.class);
    }

    public void getContactFields(){
        Call<ContactResponse> contactResponseCall = service.getContactFields();
        contactResponseCall.enqueue(new Callback<ContactResponse>() {
            @Override
            public void onResponse(Call<ContactResponse> call, Response<ContactResponse> response) {
                Log.e(TAG_LOG, "-->" + response.body().getInputFields().size());
                for (InputField inputField : response.body().getInputFields()  ){
                    Log.e(TAG_LOG, "onResponse: " + inputField.toString());
                }

            }

            @Override
            public void onFailure(Call<ContactResponse> call, Throwable t) {

            }
        });
    }
}
