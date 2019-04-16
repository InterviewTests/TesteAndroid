package br.com.ricardo.testeandroid.model.api;

import br.com.ricardo.testeandroid.model.ContactApp;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ContactService {

    public static final String BASE_URL = "https://floating-mountain-50292.herokuapp.com/";

    @GET("cells.json")
    Call<ContactApp> listFields();

}
