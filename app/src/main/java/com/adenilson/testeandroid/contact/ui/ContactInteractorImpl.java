package com.adenilson.testeandroid.contact.ui;

import com.adenilson.testeandroid.R;
import com.adenilson.testeandroid.base.BaseInteractorImpl;
import com.adenilson.testeandroid.contact.ContactInteractor;
import com.adenilson.testeandroid.contact.OnRequestContactListener;
import com.adenilson.testeandroid.networking.webservices.contact.CellsResponse;
import com.adenilson.testeandroid.networking.webservices.contact.ContactAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author: Adenilson N. da Silva Junior
 * On date: 10/06/2018
 */

public class ContactInteractorImpl extends BaseInteractorImpl implements ContactInteractor {

    @Override
    public void getContact(final OnRequestContactListener listener) {
        ContactAPI api = createApi(ContactAPI.class);
        Call<CellsResponse> call = api.getCells();
        call.enqueue(new Callback<CellsResponse>() {
            @Override
            public void onResponse(Call<CellsResponse> call, Response<CellsResponse> response) {
                if(response.isSuccessful()){
                    listener.onRequestContactSuccess(response.body());
                }else{
                    listener.onRequestContactFailed(R.string.generic_error);
                }
            }

            @Override
            public void onFailure(Call<CellsResponse> call, Throwable t) {
                listener.onRequestContactFailed(R.string.generic_error);
            }
        });
    }
}
