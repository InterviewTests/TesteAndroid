package com.alex.testeandroid.data.remote.services;

import com.google.gson.JsonObject;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by Alex on 27/08/18.
 */
public interface ContactService {

    @GET("cells.json")
    Single<JsonObject> getContactForm();
}
