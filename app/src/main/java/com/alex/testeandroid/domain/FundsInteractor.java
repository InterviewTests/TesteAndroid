package com.alex.testeandroid.domain;

import com.alex.testeandroid.data.entities.funds.Funds;
import com.alex.testeandroid.data.remote.ServiceGenerator;
import com.alex.testeandroid.data.remote.services.FundsService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * Created by alex on 27/08/18.
 */
public class FundsInteractor extends BaseInteractor {

    //region METHODS
    //region PUBLIC METHODS
    public Single<Funds> getFunds() {
        FundsService service = ServiceGenerator.createService(FundsService.class, "https://floating-mountain-50292.herokuapp.com/");
        return service.getFunds()
                .map(new Function<JsonObject, Funds>() {
                    @Override
                    public Funds apply(JsonObject jsonObject) throws Exception {
                        Gson gson = new GsonBuilder()
                                .create();

                        return gson.fromJson(jsonObject.get("screen"), Funds.class);
                    }
                });
    }
    //endregion
    //endregion
}
