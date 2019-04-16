package br.com.ricardo.testeandroid.model;

import br.com.ricardo.testeandroid.model.api.ContactService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactInteractorImpl implements ContactInteractor {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ContactService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ContactService service = retrofit.create(ContactService.class);

    @Override
    public Call<ContactApp> requestContactsField() {
        return service.listFields();
    }
}
