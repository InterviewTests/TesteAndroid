package br.com.ricardo.testeandroid.model;

import retrofit2.Call;

public interface ContactInteractor {

    Call<ContactApp> requestContactsField();

}
